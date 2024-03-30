#!/bin/bash
function g() {
  cd "$1"
  grep -R -E 'Event<[^>]+> [^ ]+ =' -o
  cd -
}

diff \
  <(g "../architectury-api/common/src/main/java/dev/architectury/event/events") \
  <(g "./common/src/main/java/com/mrmelon54/OmniPlay/event/events")
