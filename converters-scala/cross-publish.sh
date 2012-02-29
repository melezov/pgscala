#!/bin/bash

echo Cross-publishing the project ...
`dirname $0`/sbt.sh --no-jrebel "$@" builder/run +builder/proxy +publish
