@echo off
setlocal

rem ################# CONFIGURATION SECTION #################
set START_HOME=%CD%
set RUN_HOME=D:\work

set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_131
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_131
set WILDFLY_HOME_DIR=%RUN_HOME%\wildfly-9.0.1.Final
set JBOSS_HOME=%RUN_HOME%\wildfly-9.0.1.Final
set TOMCAT_HOME=%START_HOME%\apache-tomcat-7.0.79
set SERVER_DEPLOY_DIR=%TOMCAT_HOME%\webapps

set WILDFLY_ZIP_FILE=D:\programms\wildfly-9.0.1.Final.zip

rem set DISTRIB_ZIP_FILE=%START_HOME%\monopoly\modules\webmytest
set RELEASE_TEMP_DIR=%START_HOME%\monopoly\modules\webmytest\target
set INSTALLATION_DIR=%START_HOME%
rem ###################################################
 
echo Stop server
cd %TOMCAT_HOME%\bin
call %TOMCAT_HOME%\bin\shutdown.bat
 
cd %RELEASE_TEMP_DIR%

echo Remove old release
del %SERVER_DEPLOY_DIR%\ROOT.war
del %SERVER_DEPLOY_DIR%\ROOT
timeout 3

echo Copy relese to server
cd %START_HOME%
copy %RELEASE_TEMP_DIR%\*.war %SERVER_DEPLOY_DIR%\
echo Rename to ROOT 
rename %SERVER_DEPLOY_DIR%\*.war ROOT.war
timeout 3


echo Start server
cd %TOMCAT_HOME%\bin
call %TOMCAT_HOME%\bin\startup.bat

cd %START_HOME%
rem remove pauses
rem set NOPAUSE=true
rem echo Remove old release files from %RELEASE_TEMP_DIR% and unzip the latest release from %DISTRIB_ZIP_FILE% to %RELEASE_TEMP_DIR%
rem rmdir /s /q  %RELEASE_TEMP_DIR%
rem unzip -q %DISTRIB_ZIP_FILE% -d %RELEASE_TEMP_DIR%
 
rem echo Load properties from %RELEASE_TEMP_DIR%\appserver\deploy\deploy.properties
rem for /f "delims=" %%A in (%RELEASE_TEMP_DIR%\appserver\deploy\deploy.properties) do set %%A
 
rem echo Backup %WILDFLY_HOME_DIR% folder and unzip %WILDFLY_ZIP_FILE% to %INSTALLATION_DIR%
rem if exist %WILDFLY_HOME_DIR% (
rem    for %%A in ("%WILDFLY_HOME_DIR%") do (
rem        if "%time:~0,1%" == " " (
rem            rename %WILDFLY_HOME_DIR% %%~nxA_backup_%date:~7,2%-%date:~4,2%-%date:~10,4%_0%time:~1,1%-%time:~3,2%
rem        ) else (
rem            rename %WILDFLY_HOME_DIR% %%~nxA_backup_%date:~7,2%-%date:~4,2%-%date:~10,4%_%time:~0,2%-%time:~3,2%
rem        )
rem    )  
rem )
rem unzip -q %WILDFLY_ZIP_FILE% -d %INSTALLATION_DIR%
 
rem echo Copy config files from %RELEASE_TEMP_DIR%\appserver\wildfly\ to %WILDFLY_HOME_DIR%
rem xcopy /s /Y /q %RELEASE_TEMP_DIR%\appserver\wildfly\*.* %WILDFLY_HOME_DIR%
rem rem Replace tdp.beanpolicies.properties file with updated version 
rem xcopy /s /Y %TDP_BEANPOLICY_FILE% %WILDFLY_HOME_DIR%\TDP-config
 
rem echo Add new user to WildFly configuration (%jbossuser%/%jbosspassword%)
rem cd %WILDFLY_HOME_DIR%/bin
rem call %WILDFLY_HOME_DIR%/bin/add-user.bat %jbossuser% %jbosspassword%
 
 
rem echo Start WildFly
rem cd %RUN_HOME%
rem set wildfly_window_id=WildFly%time%%random%
rem start "%wildfly_window_id%" %RUN_HOME%/start-widfly.bat
rem timeout 30
 
 
rem cd %RELEASE_TEMP_DIR%\appserver\deploy
rem echo Add support modules and system configuration (deploy.cli)
rem call %WILDFLY_HOME_DIR%/bin/jboss-cli.bat --properties=%RELEASE_TEMP_DIR%\appserver\deploy\deploy.properties --file=%RELEASE_TEMP_DIR%\appserver\deploy\deploy.cli | find "The batch executed successfully" || echo There was an error while running deploy.cli! Stop deployment process. && goto :endofdeployment
 
 
rem echo Add support modules and system configuration (deploy-custom.cli)
rem call %WILDFLY_HOME_DIR%/bin/jboss-cli.bat --properties=%RELEASE_TEMP_DIR%\appserver\deploy\deploy.properties --file=%RELEASE_TEMP_DIR%\appserver\deploy\deploy-custom.cli | find "The batch executed successfully" || echo There was an error while running deploy-custom.cli! Stop deployment process. && goto :endofdeployment
 
 
rem echo Upload logging configuration (logging.cli)
rem call %WILDFLY_HOME_DIR%/bin/jboss-cli.bat --properties=%RELEASE_TEMP_DIR%\appserver\deploy\deploy.properties --file=%RELEASE_TEMP_DIR%\appserver\deploy\logging.cli | find "The batch executed successfully" || echo There was an error while running logging.cli! Stop deployment process. && goto :endofdeployment
 
 
rem echo Upload datasources configuration (tdp-ds.cli)
rem call %WILDFLY_HOME_DIR%/bin/jboss-cli.bat --properties=%RELEASE_TEMP_DIR%\appserver\deploy\deploy.properties --file=%UPDATED_DATASOURCES_CLI_FILE% | find "The batch executed successfully" || echo There was an error while running tdp-ds.cli! Stop deployment process. && goto :endofdeployment
 
 
rem echo Restart WildFly
rem call %WILDFLY_HOME_DIR%/bin/jboss-cli.bat connect --controller=%jbossmanagementinetaddress% --user=%jbossuser% --password=%jbosspassword%
rem call %WILDFLY_HOME_DIR%/bin/jboss-cli.bat -c --command=:shutdown
rem timeout 10
rem for /f "tokens=2 delims= " %%A in ('tasklist /v ^| findstr /i "%wildfly_window_id%"') do (taskkill /PID %%A)