#!/bin/bash

echo "==================== Note: All build jars will be in the folder called 'buildAllJars' ===================="
mkdir -p buildAllJars

projName="$(basename "$(pwd)")"

function ctrlCHandler(){
    echo "Processing the Ctrl+C"
    # shellcheck disable=SC2046
    docker rm $(docker ps -aq) -f
}
trap ctrlCHandler SIGINT

# Loop trough everything in the version properties folder
for d in versionProperties/*; do
  # Get the name of the version that is going to be compiled
  version=$(echo "$d" | sed "s/versionProperties\///" | sed "s/.properties//")
  name="$projName-$version-XXXXXXXXXX"
  tmpName="$(mktemp -d --tmpdir "$name")"
  contName="$(basename "$tmpName")"
  mkdir -p "$tmpName"
  cp -r . "$tmpName/"
  cd "$tmpName/" || exit
  git clean -Xfd > /dev/null
  cd - || exit

  (docker run --rm \
    --name="$contName" \
    --user "$UID":"$GID" \
    -w /app \
    -v "$tmpName":/app \
    eclipse-temurin:17-jdk-alpine \
    ./buildsingledocker.sh "$version" \
    > "$version.build.log" 2>&1; echo "Build of $version finished" )&
done

sleep 0.1 # For sequential output
echo "Waiting for processes to finish"
wait
sleep 0.1
echo "All processes finished"
