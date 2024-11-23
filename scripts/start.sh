#!/bin/bash

ROOT_PATH="/home/ec2-user/backend"
JAR="$ROOT_PATH/application.jar"
APP_LOG="$ROOT_PATH/application.log"
ERROR_LOG="$ROOT_PATH/error.log"
START_LOG="$ROOT_PATH/start.log"
NOW=$(date +%c)

echo "[$NOW] $JAR copy" >> $START_LOG
cp "$ROOT_PATH/build/libs/backend.jar" "$JAR"

echo "[$NOW] > $JAR execute" >> $START_LOG
nohup java -Duser.timezone=Asia/Seoul -jar $JAR > $APP_LOG 2> $ERROR_LOG &

SERVICE_PID=$(pgrep -f $JAR)
echo "[$NOW] > service PID: $SERVICE_PID" >> $START_LOG