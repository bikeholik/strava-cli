package com.github.bikeholik.stravacli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import io.swagger.client.ApiClient
import io.swagger.client.auth.OAuth
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import java.io.File
import java.security.KeyStore
import javax.crypto.spec.SecretKeySpec

class MainCommand(val oAuthClient: OAuthClient, val authChannel: Channel<Tokens>, val apiClient: ApiClient) : CliktCommand(name = "strava-cli") {

    private val anonymous by option("--anonymous").flag()
    private val keyStoreFile = File(".db.pkcs12")
    private val log = logger()

    override fun run() {
        if (keyStoreFile.exists() && !anonymous) {
            val keyStore = KeyStore.getInstance(keyStoreFile, "".toCharArray())
            val key = keyStore.getEntry("id", KeyStore.PasswordProtection("".toCharArray())) as KeyStore.SecretKeyEntry
            val token = String(key.secretKey.encoded)
            log.info("Token read: {}", token)
            val tokens = oAuthClient.refreshToken(token)
            configure(tokens)
        }
        if (!isAuthorized()) {
            runBlocking {
                authorize()
            }
        }
    }

    private fun isAuthorized(): Boolean {
        return getOAuth().accessToken != null
    }

    private suspend fun authorize() {
        oAuthClient.browse()
        val tokens = authChannel.receive()
        configure(tokens)
    }

    private fun configure(tokens: Tokens) {
        getOAuth().accessToken = tokens.accessToken
        if (!anonymous) {
            val keyStore = getKeyStore()
            val key = tokens.refreshToken.toByteArray()
            keyStore.setEntry("id", KeyStore.SecretKeyEntry(SecretKeySpec(key, "AES")), KeyStore.PasswordProtection("".toCharArray()))
            keyStoreFile.outputStream().use { it ->
                keyStore.store(it, "".toCharArray())
            }
        }
    }

    private fun getKeyStore(): KeyStore {
        if (keyStoreFile.exists()) {
            return KeyStore.getInstance(keyStoreFile, "".toCharArray())
        } else {
            val ks = KeyStore.getInstance("PKCS12")
            ks.load(null, null)
            return ks
        }
    }

    private fun getOAuth() = (apiClient.authentications.get("strava_oauth") as OAuth)
}