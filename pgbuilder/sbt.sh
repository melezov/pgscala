#!/bin/bash

PROJECT_DIR=`dirname $(readlink -f $0)`
PROJECT=`basename $PROJECT_DIR`

$PROJECT_DIR/../sbt.sh "project $PROJECT" "$@"
