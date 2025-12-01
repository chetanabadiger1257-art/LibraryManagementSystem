@echo off
echo Building Library Management System with Maven...
echo =============================================

mvn clean compile

echo.
echo Build completed!
echo.

echo To run the application, use: mvn exec:java -Dexec.mainClass="com.library.management.LibraryManagementSystem"

echo.
pause