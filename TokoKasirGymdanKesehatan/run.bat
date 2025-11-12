@echo off
REM ========================================
REM SISTEM KASIR TOKO - BUILD & RUN SCRIPT
REM ========================================

echo.
echo ╔══════════════════════════════════════╗
echo ║  SISTEM KASIR TOKO - BUILD & RUN    ║
echo ╚══════════════════════════════════════╝
echo.

REM Set working directory
cd /d "c:\Users\Muhammad Zidane A\Documents\Code\Java\TokoKasir"

REM Check if we're in the right directory
if not exist src\ (
    echo ERROR: Cannot find src\ folder
    echo Make sure you're in the correct directory
    pause
    exit /b 1
)

if not exist lib\ (
    echo ERROR: Cannot find lib\ folder
    echo Make sure you're in the correct directory
    pause
    exit /b 1
)

echo [1/5] Cleaning old files...
if exist tokokasir.db del tokokasir.db
if exist bin\ rmdir /s /q bin
mkdir bin
echo      ✓ Cleanup complete

echo.
echo [2/5] Compiling Java files...
javac -d bin src\*.java
if %errorlevel% neq 0 (
    echo ERROR: Compilation failed!
    pause
    exit /b 1
)
echo      ✓ Compilation successful

echo.
echo [3/5] Verifying files...
if not exist bin\ (
    echo ERROR: bin folder not created
    pause
    exit /b 1
)
echo      ✓ Files verified

echo.
echo [4/5] Starting application...
echo      (Window will appear shortly...)
echo.

REM Run the application
java -cp "bin;lib/sqlite-jdbc-3.51.0.0.jar" Main

REM If we get here, the application closed
echo.
echo ╔══════════════════════════════════════╗
echo ║     APPLICATION CLOSED               ║
echo ╚══════════════════════════════════════╝
echo.
pause
