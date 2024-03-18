#!/bin/sh

echo "==================== Note: All build jars will be in the folder called 'buildAllJars' ===================="
mkdir -p buildAllJars | true

# Loop trough everything in the version properties folder
for d in versionProperties/*; do
  # Get the name of the version that is going to be compiled
  version=$(echo "$d" | sed "s/versionProperties\///" | sed "s/.properties//")

  sh buildsingle.sh "$version" || true
  # The "| true" at the end of those are just to make sure the script continues even if a build fails
done
