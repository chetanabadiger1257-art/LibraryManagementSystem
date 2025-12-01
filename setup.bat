@echo off
echo Library Management System Setup
echo ===============================

echo Creating database schema...
echo Please make sure MySQL is running and you have root access
echo.

mysql -u root -p < database\schema.sql

echo.
echo Database setup completed!
echo.

echo Compiling Java source files...
mkdir classes
javac -d classes -cp "lib/*;src/main/java" src/main/java/com/library/management/*.java src/main/java/com/library/management/*/*.java src/main/java/com/library/management/*/*/*.java

echo.
echo Compilation completed!
echo.

echo To run the application, use the following command:
echo java -cp "classes;lib/*" com.library.management.LibraryManagementSystem

echo.
echo Setup completed successfully!
pause