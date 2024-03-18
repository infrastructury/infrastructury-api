#!/bin/sh
mkdir -p /workdir
cp -r /app -t /workdir
sh buildsingle.sh "$1"
