#!/usr/bin/env bash

VERSION=${1:-latest}

./gradlew clean build -Pprod -PnodeInstall

sudo docker build --tag strava-cli:${VERSION}
