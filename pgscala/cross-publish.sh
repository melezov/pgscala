#!/bin/bash

echo Cross-publishing the project ...
`dirname $0`/sbt.sh "$@" clean builder/run +builder/proxy +compile +publish
