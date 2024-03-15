#!/bin/sh

echo "==================== Note: All build jars will be in the folder called 'buildAllJars' ===================="
mkdir -p buildAllJars || true

version="$1"

# Clean out the folders, build it, and merge it
echo "==================== Cleaning workspace to build $version ===================="
sh gradlew clean -PmcVer="$version" --no-daemon
echo "====================Building $version ===================="
sh gradlew build -PmcVer="$version" --no-daemon
echo "==================== Merging $version ===================="
sh gradlew mergeJars -PmcVer="$version" --no-daemon
echo "==================== Moving jar ===================="
mv Merged/*.jar buildAllJars/
