#!/bin/bash

# Build Script for Calculator App
set -e

echo "=========================================="
echo "Building Calculator Application"
echo "=========================================="

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "Maven is not installed. Please install Maven first."
    exit 1
fi

# Clean and build
echo "Running Maven clean package..."
mvn clean package

# Check build status
if [ $? -eq 0 ]; then
    echo ""
    echo "=========================================="
    echo "Build successful!"
    echo "JAR file: target/calculator-app.jar"
    echo "=========================================="
    echo ""
    echo "To run the application:"
    echo "  Menu mode:        java -jar target/calculator-app.jar"
    echo "  Interactive mode: java -jar target/calculator-app.jar --interactive"
    echo ""
    echo "To build Docker image:"
    echo "  docker build -t calculator-app:1.0.0 ."
else
    echo "Build failed!"
    exit 1
fi
