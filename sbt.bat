@echo off
pushd "%~dp0"

rem This script will configure the JVM memory constraints and fire up the SBT instance.
rem If no arguments were provided to the script, SBT will start in shell mode.

rem -XX:MaxPermSize JVM option is not required if you are running on JVM 8+
rem input.encoding is neccessary to fix jline problems in SBT shell

java ^
  -XX:MaxPermSize=256m ^
  -Dinput.encoding=Cp1252 ^
  -Xss2m -Xms1g -Xmx1g ^
  -XX:+TieredCompilation -XX:+CMSClassUnloadingEnabled ^
  -XX:+UseNUMA -XX:+UseParallelGC ^
  -jar project\strap\gruj_vs_sbt-launch-0.13.x.jar ^
  %*

popd 
