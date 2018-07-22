@echo off
SET JDK_DIRECTORY=C:\Program Files\Java\jdk1.6.0_45\bin
"%JDK_DIRECTORY%\javac.exe" %~dp0\..\Source\src\contextquickie\tools\Registry.java -d %~dp0
"%JDK_DIRECTORY%\javah.exe" contextquickie.tools.Registry