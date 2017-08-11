#!/bin/sh

APP_NAME=`find ./ -maxdepth 1 -name '*.jar' | awk '{print $1}'| awk -F '/' '{print $2}'`

echo "APP_NAME:$APP_NAME"

tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'Stop Process...'
    kill -15 $tpid
fi
sleep 3
tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'Kill Process!'
    kill -9 $tpid
else
    echo 'Stop Success!'
fi
