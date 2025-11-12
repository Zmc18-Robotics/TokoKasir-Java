#!/usr/bin/env powershell

# ========================================
# SISTEM KASIR TOKO - BUILD & RUN SCRIPT
# ========================================

Write-Host ""
Write-Host "╔══════════════════════════════════════╗" -ForegroundColor Cyan
Write-Host "║  SISTEM KASIR TOKO - BUILD & RUN    ║" -ForegroundColor Cyan
Write-Host "╚══════════════════════════════════════╝" -ForegroundColor Cyan
Write-Host ""

# Set working directory
Set-Location "c:\Users\Muhammad Zidane A\Documents\Code\Java\TokoKasir"

# Check if we're in the right directory
if (!(Test-Path "src")) {
    Write-Host "ERROR: Cannot find src\ folder" -ForegroundColor Red
    Write-Host "Make sure you're in the correct directory" -ForegroundColor Red
    Read-Host "Press Enter to exit"
    exit 1
}

if (!(Test-Path "lib")) {
    Write-Host "ERROR: Cannot find lib\ folder" -ForegroundColor Red
    Write-Host "Make sure you're in the correct directory" -ForegroundColor Red
    Read-Host "Press Enter to exit"
    exit 1
}

Write-Host "[1/5] Cleaning old files..." -ForegroundColor Yellow
Remove-Item -Force "tokokasir.db" -ErrorAction SilentlyContinue
Remove-Item -Recurse -Force "bin" -ErrorAction SilentlyContinue
New-Item -ItemType Directory -Name "bin" -Force | Out-Null
Write-Host "      ✓ Cleanup complete" -ForegroundColor Green

Write-Host ""
Write-Host "[2/5] Compiling Java files..." -ForegroundColor Yellow
$result = & javac -d bin "src\*.java" 2>&1
if ($LASTEXITCODE -ne 0) {
    Write-Host "ERROR: Compilation failed!" -ForegroundColor Red
    Write-Host $result
    Read-Host "Press Enter to exit"
    exit 1
}
Write-Host "      ✓ Compilation successful" -ForegroundColor Green

Write-Host ""
Write-Host "[3/5] Verifying files..." -ForegroundColor Yellow
if (!(Test-Path "bin")) {
    Write-Host "ERROR: bin folder not created" -ForegroundColor Red
    Read-Host "Press Enter to exit"
    exit 1
}
Write-Host "      ✓ Files verified" -ForegroundColor Green

Write-Host ""
Write-Host "[4/5] Starting application..." -ForegroundColor Yellow
Write-Host "      (Window will appear shortly...)" -ForegroundColor Gray
Write-Host ""

# Run the application
& java -cp "bin;lib/sqlite-jdbc-3.51.0.0.jar" Main

# If we get here, the application closed
Write-Host ""
Write-Host "╔══════════════════════════════════════╗" -ForegroundColor Cyan
Write-Host "║     APPLICATION CLOSED               ║" -ForegroundColor Cyan
Write-Host "╚══════════════════════════════════════╝" -ForegroundColor Cyan
Write-Host ""
Read-Host "Press Enter to exit"
