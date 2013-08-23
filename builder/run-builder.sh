#!/bin/bash

echo Running builder ...
`dirname $0`/sbt.sh --no-jrebel "$@" run +proxy
