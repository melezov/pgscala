#!/bin/bash

echo Running builder ...
`dirname $0`/sbt.sh "$@" run +proxy
