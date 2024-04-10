#!/bin/bash
mapp() { while IFS= read -r line; do "$1" "$2" "$line"; done; }

hashKey() {
  # replace non-alphanumeric characters with underscore to make keys valid BASH identifiers
  echo "$1_$2" | sed -E "s/[^a-zA-Z0-9]+/_/g" | sed -E "s/^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+\$//g"
}

hashPut() {
  local KEY=$(hashKey "$1" "$2")
  eval "$KEY"="$3"
}

hashGet() {
  local KEY=$(hashKey "$1" "$2")
  echo "${!KEY}"
}

MC_VER=0
readarray -d '' entries < <(printf '%s\0' versionProperties/*.properties | sort -zV)
for entry in "${entries[@]}"; do
  ((++MC_VER))
  entry="${entry:18:6}"
  hashPut MC "$entry" "$MC_VER"
done

lastFile=""
allowIfLayer=()

function g2() {
  ver="$(hashGet MC "$1")"
  currFile="$(cut -d : -f 1 <<< "$2")"
  line="$(cut -d : -f 2 <<< "$2")"
  if [[ "${line:0:3}" == "#if" ]]; then
    if [[ "${#allowIfLayer[@]}" -ne "0" && "${allowIfLayer[-1]}" -ne "0" ]]; then
      # auto deny if previous layer is denied
      allowIfLayer+=("1")
    else
      ifcond="$(sed -re 's/#if MC_VER ([^ ]+) MC_([0-9]+_[0-9]+(_[0-9]+)?)(.+?)/\1/' <<< "$line")"
      ifver="$(sed -re 's/#if MC_VER ([^ ]+) MC_([0-9]+_[0-9]+(_[0-9]+)?)(.+?)/\2/' <<< "$line")"
      tooMuch="$(sed -re 's/#if MC_VER ([^ ]+) MC_([0-9]+_[0-9]+(_[0-9]+)?)(.+?)/\2/' <<< "$line")"
      if [[ -z "$tooMuch" ]]; then
        echo "Too much triggered for line: $line"
        exit 1
      fi

      # 0 if matches
      matches=0

      ifver="$(hashGet MC "$ifver")"
      case "$ifcond" in
        "==")
          matches=$( [[ "$ver" -eq "$ifver" ]] ; echo $? )
        ;;
        "!=")
          matches=$( [[ "$ver" -ne "$ifver" ]] ; echo $? )
        ;;
        ">")
          matches=$( [[ "$ver" -gt "$ifver" ]] ; echo $? )
        ;;
        "<")
          matches=$( [[ "$ver" -lt "$ifver" ]] ; echo $? )
        ;;
        ">=")
          matches=$( [[ "$ver" -ge "$ifver" ]] ; echo $? )
        ;;
        "<=")
          matches=$( [[ "$ver" -le "$ifver" ]] ; echo $? )
        ;;
        *)
          echo "Unknown condition: $ifcond"
          exit 1
        ;;
      esac
      allowIfLayer+=("$matches")
    fi
  elif [[ "${line:0:6}" == "#endif" ]]; then
    unset 'allowIfLayer[${#allowIfLayer[@]}-1]'
  elif [[ "${#allowIfLayer[@]}" -eq "0" || "${allowIfLayer[${#allowIfLayer[@]}-1]}" -eq "0" ]]; then
    echo "$line"
  fi
}

function g() {
  cd "$1" || exit
  grep -R -E 'Event<[^>]+> [^ ]+ =|^\w*#(if (.+$)|endif)' -o | mapp g2 "$2"
  cd - || exit
}

for entry in "${entries[@]}"; do
  entry="${entry:18:6}"
  echo "=== Diff $entry ==="
  echo "Entries with a left arrow are in Architectury but missing from Infrastructury"
  diff \
    <(g "../architectury-api/common/src/main/java/dev/architectury/event/events") \
    <(g "./common/src/main/java/com/mrmelon54/infrastructury/event/events" "$entry")
done
