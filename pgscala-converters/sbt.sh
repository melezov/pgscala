#!/bin/bash

`dirname $(readlink -f $0)`/../sbt.sh "project PGScala-Converters" "$@"
