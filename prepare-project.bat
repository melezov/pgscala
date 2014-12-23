@echo off

rem SBT will not download the project dependencies unless some action triggers it.
rem This script will force this download by issueing the "update" command.
rem If successful, an Eclipse project will be created (SBT will generate .project and .classpath files).

echo Downloading dependencies and creating an Eclipse project ...
call "%~dp0sbt.bat" +update eclipse %*
