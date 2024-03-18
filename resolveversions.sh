#!/bin/bash

versions=()

# Loop trough everything in the version properties folder
for d in versionProperties/*; do
  # Get the name of the version that is going to be compiled
  version=$(echo "$d" | sed "s/versionProperties\///" | sed "s/.properties//")
  versions+=("{\"mc\":\"$version\"}")
done

versionsJson=$(jo -a -- "${versions[@]}")

echo "matrix={\"include\":${versionsJson}}"
