@echo off

echo Cross-publishing the project ...
call "%~dp0sbt.bat" %* clean builder/run +builder/proxy +compile +publish
