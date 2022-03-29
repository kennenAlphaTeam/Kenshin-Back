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

echo "> 어플 구동 확인"
i=1
IS_DONE=1
while [ $i -le 5 ]
do
  echo ">> $i 번째 시도"
  sleep 5
  HEALTH1=$(curl -s http://127.0.0.1:8080/actuator/health)
  HEALTH2=$(curl -s https://127.0.0.1:8080/actuator/health --insecure)
  echo "$HEALTH1 $HEALTH2" | grep UP > /dev/null
  IS_DONE=$?
  echo $IS_DONE
  if [ $IS_DONE -eq 0 ]
  then
    break
  fi
  ((i++))
done

if [ $IS_DONE -eq 0 ]
then
  echo "> 배포 완료"
else
  echo "> 배포 실패"
fi
exit $IS_DONE