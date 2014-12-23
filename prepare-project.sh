#!/bin/bash

# SBT will not download the project dependencies unless some action triggers it.
# This script will force this download by issueing the "update" command.
# If successful, an Eclipse project will be created (SBT will generate .project and .classpath files).

echo Downloading dependencies and creating an Eclipse project ...
exec "$( dirname "$0" )/sbt.sh" +update eclipse "$@"
