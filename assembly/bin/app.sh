#! /bin/sh

PROJECT=${project.artifactId}
VERSION=${project.version}

APP_PID=0
APP_NAME=${PROJECT}-${VERSION}

usage() {
  cat << -EOF-
Usage: `basename $0` -p <port> (start|restart|stop|status|debug)
-EOF-
  exit 1
}

while getopts "p:" opt ; do
  case $opt in
    p) APP_PORT=$OPTARG ;;
  esac
done
shift `expr $OPTIND - 1`

DIR=$(cd `dirname $0`; pwd)
WORK_DIR="$(cd `dirname $0`; pwd)/work.${APP_PORT}"
APP_RUNNING_ID="${WORK_DIR}/running.pid"
APP_RUNNING_LOG="${WORK_DIR}/running.log"
JAR_FILE="${DIR}/${APP_NAME}.jar"
JAVA_OPTS="-server -Xms1024m -Xmx1024m -Xloggc:${WORK_DIR}/logs/gc.log"
JAVA_DEBUG_PORT=9999
JAVA_DEBUG="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,suspend=n,address=${JAVA_DEBUG_PORT}"

mkdir -p ${WORK_DIR}/logs
cd $WORK_DIR

getpid(){
    if [ -f ${APP_RUNNING_ID} ]; then
        APP_PID=`cat $APP_RUNNING_ID`
        if [ ! -d /proc/$APP_ID ]; then
          APP_PID=0
        fi
    #else
    #    pid=`jps -l | grep ${APP_NAME}`
    #    if [ -n "$pid" ]; then
    #        APP_PID=`echo $pid| awk '{print $1}'`
    #    else
    #        APP_PID=0
    #    fi
    fi
}

do_start(){
    if [ -z ${SPRING_PROFILES_ACTIVE} ]; then
      echo "requires OS environment, please 'export SPRING_PROFILES_ACTIVE=xx'"
      usage
      exit 1
    fi

    getpid

    if [ "$APP_PID" -ne 0 ]; then
        echo "$APP_NAME #$APP_PID already started"
    else
        nohup java $JAVA_OPTS -jar ${JAR_FILE} --server.port=${APP_PORT} $@ > ${APP_RUNNING_LOG} 2>&1 &
        echo $! > ${APP_RUNNING_ID}

        getpid

        if [ "$APP_PID" -ne 0 ]; then
            echo "$APP_NAME #$APP_PID is starting ..."
        else
            echo "$APP_NAME start fail"
        fi
    fi
}

do_stop(){
    getpid
    if [ "$APP_PID" -ne 0 ]; then
        kill -9 $APP_PID
        if [ $? -eq 0 ]; then
            rm -fr ${APP_RUNNING_ID}
            echo "$APP_NAME is stopped"
        else
            echo "$APP_NAME stop fail"
        fi
    else
        echo "$APP_NAME is not running"
    fi
}

do_status(){
    getpid
    if [ "$APP_PID" -ne 0 ]; then
        echo "$APP_NAME #$APP_PID is running"
    else
        echo "$APP_NAME is not running"
    fi
}

case "$1" in
    start)
        shift
        do_start $@
        ;;
    stop)
        do_stop
        ;;
    restart)
        shift
        do_stop
        if [ $? -eq 0 ]; then
          do_start $@
        fi
        ;;
    status)
        do_status
        ;;
    debug)
        shift
        JAVA_OPTS="${JAVA_OPTS} ${JAVA_DEBUG}"
        do_start $@
        echo "debug listening port: ${JAVA_DEBUG_PORT}"
        ;;
    *)
        usage
esac