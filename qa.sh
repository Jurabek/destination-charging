#!/bin/sh

./gradlew sonarqube \
  -Dsonar.jacoco.reportPaths=build/jacoco/test.exec \
  -Dsonar.projectKey=destination-charging \
  -Dsonar.organization=jurabek-github \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.login=26c2b4fc110f0f7cc5c1ea9a1241df4532b6e70f