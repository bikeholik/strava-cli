#!/usr/bin/env bash

VERSION=${1:-latest}

./gradlew clean build -Pprod -PnodeInstall -x test

sudo docker build --tag gcr.io/strava-cli/strava-cli:${VERSION} . && \
 sudo docker push gcr.io/strava-cli/strava-cli:${VERSION}
