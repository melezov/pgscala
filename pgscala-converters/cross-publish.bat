@echo off

echo Cross-publishing the project ...
call "%~dp0\sbt.bat" --no-jrebel %* +publish
