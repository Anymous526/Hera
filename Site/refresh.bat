@echo off
set curdir=%~dp0
set partition=%curdir:~0,1%

echo ����Ŀ¼��%curdir%
%partition%:
cd %curdir%

echo ������
call mvn clean eclipse:clean

echo ����eclipse���̻���,����JARԴ����
call mvn eclipse:eclipse -Dwtpversion=1.0 -DdownloadSources=true

echo ���ɹ�������JAR��,�ŵ�WEB-INF/lib
cd %curdir%src\main\webapp\WEB-INF\lib\
del *.jar
cd %curdir%
call mvn dependency:copy-dependencies

echo MAVEN���̹������
pause