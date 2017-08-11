#!/bin/sh
echo "input env:"
read env

APP_NAME=`find ./ -maxdepth 1 -name '*.jar' | awk '{print $1}'| awk -F '/' '{print $2}'`

LOG_NAME=`echo "$APP_NAME" | awk -F '.jar' '{print $1}'`

echo "starting app: $APP_NAME with env:$env..."

#jvm options modify with your environment
JAVA_OPTS="-server -Xms512m -Xmx512m -Xmn512m -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=5
 -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection -XX:CMSMaxAbortablePrecleanTime=500 -XX:+CMSClassUnloadingEnabled
 -verbose.gc -XX:+PrintGCDetails  -XX:+PrintGCTimeStamps -Xloggc:/tmp/logs/$LOG_NAME-gc.log -Djava.awt.headless=true"

#start
nohup java -Dspring.profiles.active=$env $JAVA_OPTS -jar $APP_NAME > /tmp/logs/$LOG_NAME.log 2>&1 &

echo $! > tpid

echo "Start Success!"
