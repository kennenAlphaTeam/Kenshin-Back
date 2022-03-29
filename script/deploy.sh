SCRIPT=$(readlink -f "$0")
REPO=$(dirname "$SCRIPT")

echo "> 실행중인 어플리케이션 확인"
CURRENT_PID=$(pgrep -f java)
echo ">> Current pid : $CURRENT_PID"

if [ -z $CURRENT_PID ]; then
  echo ">> 구동 중인 어플리케이션이 없습니다"
else
  echo ">> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> 어플 실행"

JAR_NAME=$(ls $REPO | grep 'jar' | tail -n 1)

echo ">> jar file : $JAR_NAME"
JAVA_OPTION=-Dspring.profiles.active=prod

BUILD_NAME=$(echo "$JAR_NAME" | cut -f 1 -d '.')
nohup java $JAVA_OPTION -jar "$REPO/$JAR_NAME" > "$HOME/app/log/back-$BUILD_NAME.log" &

sleep 10

HEALTH=$(curl https://localhost:8080/actuator/health --insecure)
echo "$HEALTH" | grep UP
STATE=$?
echo $STATE
if [ $STATE -eq 0 ]
then
        echo "> 배포 완료"
else
        echo "> 배포 실패"
fi
exit $STATE