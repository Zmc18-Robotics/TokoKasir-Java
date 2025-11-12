@echo off
REM Compile all Java files
echo Compiling Java files...
javac -d bin src/*.java

if %ERRORLEVEL% EQU 0 (
    echo Compilation successful!
    echo.
    echo Running application...
    java -cp bin;lib/sqlite-jdbc-3.44.0.0.jar Main
) else (
    echo Compilation failed!
    pause
)
