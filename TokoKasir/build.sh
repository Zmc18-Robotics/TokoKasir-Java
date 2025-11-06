#!/bin/bash

# Compile all Java files
echo "Compiling Java files..."
javac -d bin src/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo ""
    echo "Running application..."
    java -cp bin:lib/sqlite-jdbc-3.44.0.0.jar Main
else
    echo "Compilation failed!"
    exit 1
fi
