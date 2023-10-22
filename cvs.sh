#!/bin/bash

mvn clean verify sonar:sonar \
  -Dsonar.projectKey=fr.uga.miage.m1:persistence_g1_3 \
  -Dsonar.host.url=http://im2ag-sonar.u-ga.fr:9000 \
  -Dsonar.login=60aaa0ffdfd747906f2a0f3bd29895a25efc623b
