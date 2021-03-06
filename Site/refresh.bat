@echo off
set curdir=%~dp0
set partition=%curdir:~0,1%

echo 工程目录：%curdir%
%partition%:
cd %curdir%

echo 清理工作
call mvn clean eclipse:clean

echo 构建eclipse工程环境,下载JAR源代码
call mvn eclipse:eclipse -Dwtpversion=1.0 -DdownloadSources=true

echo 生成工程依赖JAR包,放到WEB-INF/lib
cd %curdir%src\main\webapp\WEB-INF\lib\
del *.jar
cd %curdir%
call mvn dependency:copy-dependencies

echo MAVEN工程构建完成
pause