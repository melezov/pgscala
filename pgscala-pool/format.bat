@echo off

echo Formatting code via scalariform ...
call "%~dp0\sbt.bat" %* scalariform-format
