#!/bin/bash

ROOT_PATH="/home/ec2-user/backend"
JAR="$ROOT_PATH/application.jar"
STOP_LOG="$ROOT_PATH/stop.log"
SERVICE_PID=$(pgrep -f $JAR)

if [ -z "$SERVICE_PID" ]; then
  echo "service NouFound" >> $STOP_LOG
else
  echo "service terminate " >> $STOP_LOG
  kill "$SERVICE_PID"
fi