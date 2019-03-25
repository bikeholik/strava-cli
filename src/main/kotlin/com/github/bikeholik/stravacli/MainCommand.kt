package com.github.bikeholik.stravacli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.default
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

    companion object {
        const val NO_PASSWORD = ""
    }

    private val anonymous by option("--anonymous", "-a").flag()
    private val keyId by option("--user-alias", "-u").default("id")
    private val password by option("--password", "-p").convert { KeyStore.PasswordProtection(it.toCharArray()) }.default(KeyStore.PasswordProtection(NO_PASSWORD.toCharArray()))
    private val keyStoreFile = File(".db.pkcs12")
    private val log = logger()

    override fun run() {
        try {
            preAuthorizeIfPossible()
        } catch (e: Exception) {
            log.warn("Error when loading authorization data", e)
        }
        if (!isAuthorized()) {
            runBlocking {
                authorize()
            }
        }
    }

    private fun preAuthorizeIfPossible() {
        if (keyStoreFile.exists() && !anonymous) {
            val keyStore = getKeyStore()
            if (!keyStore.isKeyEntry(keyId)) {
                return;
            }
            val key = keyStore.getEntry(keyId, password) as KeyStore.SecretKeyEntry
            val token = String(key.secretKey.encoded)
            log.debug("Token read: {}", token)
            val tokens = oAuthClient.refreshToken(token)
            configure(tokens)
        }
    }

    private fun isAuthorized(): Boolean {
        return getOAuth().accessToken != null
    }

    private suspend fun authorize() {
        oAuthClient.redirectToUseBrowser()
        val tokens = authChannel.receive()
        configure(tokens)
    }

    private fun configure(tokens: Tokens) {
        getOAuth().accessToken = tokens.accessToken
        if (!anonymous) {
            val keyStore = getKeyStore()
            val key = tokens.refreshToken.toByteArray()
            keyStore.setEntry(keyId, KeyStore.SecretKeyEntry(SecretKeySpec(key, "AES")), password)
            keyStoreFile.outputStream().use {
                keyStore.store(it, NO_PASSWORD.toCharArray())
            }
        }
    }

    private fun getKeyStore(): KeyStore {
        if (keyStoreFile.exists()) {
            return KeyStore.getInstance(keyStoreFile, NO_PASSWORD.toCharArray())
        } else {
            val ks = KeyStore.getInstance("PKCS12")
            ks.load(null, null)
            return ks
        }
    }

    private fun getOAuth() = (apiClient.authentications.get("strava_oauth") as OAuth)
}
