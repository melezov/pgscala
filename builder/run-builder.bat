@echo off

echo Running builder ...
call "%~dp0sbt.bat" %* run +proxy
