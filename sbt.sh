#!/bin/bash
cd `dirname $0`

## START JVM PARAMS
JVM_PARAMS="-Xss2m -Xms2g -Xmx2g -XX:+TieredCompilation -XX:ReservedCodeCacheSize=256m -XX:MaxPermSize=256m -XX:+CMSClassUnloadingEnabled -XX:+UseNUMA -XX:+UseParallelGC -Dscalac.patmat.analysisBudget=off"

LOG_LEVEL=
NO_PAUSE=false
DO_LOOP=false

while [ -n "$*" ]
do
  case "$1" in
    "--debug")
      echo "Setting debug mode"
      LOG_LEVEL="\"set logLevel:=Level.Debug\""
      ;;
    "--prod")
      echo "Setting production mode"
      LOG_LEVEL="\"set logLevel:=Level.Info\""
      ;;
    "--loop")
      echo "Will run SBT in loop mode"
      DO_LOOP=true
      ;;
    "--no-pause")
      echo "Will not pause in loop mode"
      NO_PAUSE=true
      ;;
    *)
      SBT_PARAMS="$SBT_PARAMS \"$1\""
      ;;
  esac
  shift

done

GRUJ_PATH="project/strap/gruj_vs_sbt-launch-0.13.x.jar"
RUN_CMD="java $JVM_PARAMS -jar $GRUJ_PATH $LOG_LEVEL $SBT_PARAMS"

LOOPING=true
while $LOOPING
do
  eval "$RUN_CMD"

  if ! $DO_LOOP ; then
    LOOPING=false
  else
    if ! $NO_PAUSE ; then
      echo "Press Enter to continue or Press CTRL+C to exit!"
      read
    fi
  fi
done
