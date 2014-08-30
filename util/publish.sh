#!/bin/bash

echo Publishing the project ...
`dirname $0`/sbt.sh "$@" clean publish
