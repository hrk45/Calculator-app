@echo off
REM Build Script for Calculator App (Windows)
setlocal enabledelayedexpansion

echo ==========================================
echo Building Calculator Application
echo ==========================================

REM Check if Maven is installed
where mvn >nul 2>nul
if errorlevel 1 (
    echo Maven is not installed. Please install Maven first.
    exit /b 1
)

REM Clean and build
echo Running Maven clean package...
call mvn clean package

if errorlevel 1 (
    echo Build failed!
    exit /b 1
) else (
    echo.
    echo ==========================================
    echo Build successful!
    echo JAR file: target\calculator-app.jar
    echo ==========================================
    echo.
    echo To run the application:
    echo   Menu mode:        java -jar target\calculator-app.jar
    echo   Interactive mode: java -jar target\calculator-app.jar --interactive
    echo.
    echo To build Docker image:
    echo   docker build -t calculator-app:1.0.0 .
)

endlocal
