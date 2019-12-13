#!/bin/bash

TRIAL_TIME_LIMIT=2m

git checkout -- .

doalarm() { perl -e 'alarm shift; exec @ARGV' -- "$@"; }

for i in `seq 1000`; do
    echo $i
    TRIAL_ID=`uuidgen`
    TRIAL_DIR=../rijnards-experiments/$TRIAL_ID
    mkdir -p $TRIAL_DIR/repertoire
    # modify this repertoire. save what we did to it in the trial dir
    ../scripts/generate-in-place.sh ../templates/semantics-altering &> $TRIAL_DIR/stats.txt
    # copy it for reference
    cp * $TRIAL_DIR/repertoire
    cd ..
    # timeout $TRIAL_TIME_LIMIT ./gradlew runRepertoire # > rijnards-experiments/$TRIAL_ID/out.csv
    # ./gradlew runRepertoire # > rijnards-experiments/$TRIAL_ID/out.csv
    # ./gradlew runRepertoire > rijnards-experiments/$TRIAL_ID/out.csv
    # 20 minutes
    doalarm 1200 ./gradlew runRepertoire > rijnards-experiments/$TRIAL_ID/out.csv
    cd repertoire
    git checkout -- .
done

# Live updates:
# stdbuf -oL tail -F rijnards-experiments/scratch/out.csv | awk -F ',' '{print $1,$4,$5}'

# Inspect values 
# T=0; cat rijnards-experiments/prune-sequence-take-first/out.csv | grep "^${T}" | awk -F ',' '{print $5}' | grep "E" | sort | tail -n 1
