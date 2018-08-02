@REM ************************************************************************************
@REM Description: run Pingclient
@REM Author: Rui Moreira
@REM Date: 20/02/2009
@REM pwd: /Users/rui/Documents/NetBeansProjects/SD/src/edu/ufp/sd/helloworld
@REM http://docs.oracle.com/javase/tutorial/rmi/running.html
@REM ************************************************************************************

@REM ======================== Use Shell Parameters ========================
@REM Script usage: setenv <role> (where role should be: server / client)
@SET SCRIPT_ROLE=%1

@REM ======================== CHANGE BELOW ACCORDING YOUR PROJECT and PC SETTINGS ========================
@REM ==== PC STUFF ====
@Set WORKDRIVE=C
@Set JDK=%WORKDRIVE%:\Programas\Java\jdk1.8.0_121
@Set USERNAME=joaoc

@REM ==== JAVA NAMING STUFF ====
@Set JAVAPROJ_NAME=SD
@set JAVAPROJ=%WORKDRIVE%:\\Users\\joaoc\\Desktop\\%JAVAPROJ_NAME%
@Set PACKAGE=minesweeper
@Set SERVICE_NAME_ON_REGISTRY=Minesweeper
@Set CLIENT_CLASS_PREFIX=Minesweeper
@Set SERVER_CLASS_PREFIX=Minesweeper
@Set CLIENT_CLASS_POSTFIX=Client
@Set SERVER_CLASS_POSTFIX=Server
@Set SETUP_CLASS_POSTFIX=Setup
@Set SERVANT_IMPL_CLASS_POSTFIX=Impl
@Set SERVANT_ACTIVATABLE_IMPL_CLASS_POSTFIX=ActivatableImpl

@REM ==== NETWORK STUFF ====
@REM Must run http server on codebase host:
@REM Python 2: python -m SimpleHTTPServer 8000
@REM Python 3: python -m http.server 8000
@Set REGISTRY_HOST=localhost
@Set REGISTRY_PORT=1099
@Set SERVER_RMI_HOST=%REGISTRY_HOST%
@Set SERVER_RMI_PORT=1098
@Set SERVER_CODEBASE_HOST=%SERVER_RMI_HOST%
@Set SERVER_CODEBASE_PORT=8000
@Set CLIENT_RMI_HOST=%REGISTRY_HOST%
@Set CLIENT_RMI_PORT=1097
@Set CLIENT_CODEBASE_HOST=%CLIENT_RMI_HOST%
@Set CLIENT_CODEBASE_PORT=8000

@REM ======================== DO NOT CHANGE AFTER THIS POINT ========================
@Set JAVAPACKAGE=edu.ufp.sd.%PACKAGE%
@Set JAVAPACKAGEROLE=edu.ufp.sd.%PACKAGE%.%SCRIPT_ROLE%
@Set JAVAPACKAGEPATH=edu/ufp/sd/%PACKAGE%/%SCRIPT_ROLE%
@Set JAVASCRIPTSPATH=edu/ufp/sd/%PACKAGE%/runscripts
@REM Set JAVASECURITYPATH=edu/ufp/sd/%PACKAGE%/securitypolicies
@Set JAVASECURITYPATH=edu\\ufp\\sd\\%PACKAGE%\\securitypolicies
@Set SERVICE_NAME=%SERVICE_PREFIX%Service
@Set SERVICE_URL=rmi://%REGISTRY_HOST%:%REGISTRY_PORT%/%SERVICE_NAME%
@Set SERVANT_ACTIVATABLE_IMPL_CLASS=%JAVAPACKAGEROLE%.%SERVER_CLASS_PREFIX%%SERVANT_ACTIVATABLE_IMPL_CLASS_POSTFIX%
@Set SERVANT_PERSISTENT_STATE_FILENAME=%SERVICE_PREFIX%Persistent.State

@Set PATH=%PATH%;.;%JDK%\bin

@REM @set JAVAPROJ=%WORKDRIVE%:/Users/%USERNAME%/OneDrive/UFP/Ano3/Sem2/SD/NetBeansProjects/%JAVAPROJ_NAME%
@rem Set NETBEANS_CLASSES=build\classes\
@Set NETBEANS_CLASSES=build/classes/
@Set NETBEANS_SRC=src
@Set NETBEANS_DIST=dist
@Set NETBEANS_DIST_LIB=dist/lib

@set JAVAPROG_CLASSES_FOLDER=%JAVAPROJ%\%NETBEANS_CLASSES%
@set JAVAPROJ_DIST_FOLDER=%JAVAPROJ%\%NETBEANS_DIST%
@set JAVAPROJ_JAR_FILE=%JAVAPROJ_NAME%.jar

@set CLASSPATH=.;%JAVAPROG_CLASSES_FOLDER%
@REM @set CLASSPATH=.;%JAVAPROJ_DIST_FOLDER%/%JAVAPROJ_JAR_FILE%.jar

@Set ABSPATH2CLASSES=%JAVAPROJ%\%NETBEANS_CLASSES%
@Set ABSPATH2SRC=%JAVAPROJ%\%NETBEANS_SRC%
@Set ABSPATH2DIST=%JAVAPROJ%/%NETBEANS_DIST%

@REM #java.rmi.server.codebase property specifies the location from which classes of this server can be downloaded.
@REM Set SERVER_CODEBASE=http://%SERVER_CODEBASE_HOST%:%SERVER_CODEBASE_PORT%/%NETBEANS_CLASSES%
@REM Set CLIENT_CODEBASE=http://%CLIENT_CODEBASE_HOST%:%CLIENT_CODEBASE_PORT%/%NETBEANS_CLASSES%
@Set SERVER_CODEBASE=http://%SERVER_CODEBASE_HOST%:%SERVER_CODEBASE_PORT%/%JAVAPROJ_JAR_FILE%
@Set CLIENT_CODEBASE=http://%CLIENT_CODEBASE_HOST%:%CLIENT_CODEBASE_PORT%/%JAVAPROJ_JAR_FILE%

@REM #Policy tool editor: /Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/bin/policytool
@rem Set SERVER_SECURITY_POLICY=file:///%JAVAPROJ%/%NETBEANS_SRC%/%JAVASECURITYPATH%/serverAllPermition.policy
@rem Set CLIENT_SECURITY_POLICY=file:///%JAVAPROJ%/%NETBEANS_SRC%/%JAVASECURITYPATH%/clientAllPermition.policy
@rem Set SETUP_SECURITY_POLICY=file:///%JAVAPROJ%/%NETBEANS_SRC%/%JAVASECURITYPATH%/setup.policy
@rem Set RMID_SECURITY_POLICY=file:///%JAVAPROJ%/%NETBEANS_SRC%/%JAVASECURITYPATH%/rmid.policy
@rem Set GROUP_SECURITY_POLICY=file:///%JAVAPROJ%/%NETBEANS_SRC%/%JAVASECURITYPATH%/group.policy

@Set SERVER_SECURITY_POLICY=file:///%JAVAPROJ%\\%NETBEANS_SRC%\\%JAVASECURITYPATH%\\serverAllPermition.policy
@Set CLIENT_SECURITY_POLICY=file:///%JAVAPROJ%\\%NETBEANS_SRC%\\%JAVASECURITYPATH%\\clientAllPermition.policy
@Set SETUP_SECURITY_POLICY=file:///%JAVAPROJ%\\%NETBEANS_SRC%\\%JAVASECURITYPATH%\\setup.policy
@Set RMID_SECURITY_POLICY=file:///%JAVAPROJ%\\%NETBEANS_SRC%\\%JAVASECURITYPATH%\\rmid.policy
@Set GROUP_SECURITY_POLICY=file:///%JAVAPROJ%\\%NETBEANS_SRC%\\%JAVASECURITYPATH%\\group.policy

