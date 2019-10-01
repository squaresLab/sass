#!/bin/bash

COUNTER=0

./$1 2>&1 | grep '"number_of_matches":' | grep -v '"number_of_matches": 0'

RESULT=$?

while [ "$RESULT" -eq 0 ]; do
  ./$1 2>&1 | grep '"number_of_matches":' | grep -v '"number_of_matches": 0'
  RESULT=$?
  let COUNTER="$COUNTER"+1
  echo $COUNTER
done
