@echo off

echo Running builder ...
call "%~dp0sbt.bat" --no-jrebel %* run +proxy
