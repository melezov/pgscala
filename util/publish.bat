@echo off

echo Publishing the project ...
call "%~dp0sbt.bat" %* clean publish
