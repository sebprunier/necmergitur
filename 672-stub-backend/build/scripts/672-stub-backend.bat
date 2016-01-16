@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  672-stub-backend startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

@rem Add default JVM options here. You can also use JAVA_OPTS and STUB_BACKEND_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windowz variants

if not "%OS%" == "Windows_NT" goto win9xME_args
if "%@eval[2+2]" == "4" goto 4NT_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*
goto execute

:4NT_args
@rem Get arguments from the 4NT Shell from JP Software
set CMD_LINE_ARGS=%$

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\672-stub-backend-1.0.jar;%APP_HOME%\lib\json-20090211.jar;%APP_HOME%\lib\http-2.104.jar;%APP_HOME%\lib\jna-4.2.0.jar;%APP_HOME%\lib\protobuf-java-2.5.0.jar;%APP_HOME%\lib\simple-http-6.0.1.jar;%APP_HOME%\lib\antlr-runtime-3.5.2.jar;%APP_HOME%\lib\commons-beanutils-1.8.3.jar;%APP_HOME%\lib\less4j-1.14.0.jar;%APP_HOME%\lib\antlr4-runtime-4.5.jar;%APP_HOME%\lib\handlebars-2.2.3.jar;%APP_HOME%\lib\markdown4j-2.2-cj-1.0.jar;%APP_HOME%\lib\jackson-annotations-2.6.0.jar;%APP_HOME%\lib\snakeyaml-1.16.jar;%APP_HOME%\lib\jackson-core-2.6.2.jar;%APP_HOME%\lib\fast-classpath-scanner-1.9.0.jar;%APP_HOME%\lib\jackson-databind-2.6.2.jar;%APP_HOME%\lib\webjars-locator-core-0.27.jar;%APP_HOME%\lib\validate.js-0.8.0.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.6.2.jar;%APP_HOME%\lib\slf4j-api-1.7.12.jar;%APP_HOME%\lib\commons-compress-1.9.jar;%APP_HOME%\lib\slf4j-simple-1.7.12.jar;%APP_HOME%\lib\webjars-locator-0.28.jar;%APP_HOME%\lib\coffee-script-1.8.0.jar;%APP_HOME%\lib\commons-io-2.4.jar;%APP_HOME%\lib\commons-lang3-3.4.jar;%APP_HOME%\lib\guava-18.0.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\simple-common-6.0.1.jar;%APP_HOME%\lib\simple-transport-6.0.1.jar

@rem Execute 672-stub-backend
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %STUB_BACKEND_OPTS%  -classpath "%CLASSPATH%" fr.paris.necmergitur.Application %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable STUB_BACKEND_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%STUB_BACKEND_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
