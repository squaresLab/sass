#!/bin/bash

# this script modified to run experiment for each template individually
# no timeout

# restore the repertoire to its initial state from git
git checkout -- .

TEMPLATE_DIR=../templates/semantics-altering

NUM_TEMPLATES=`ls $TEMPLATE_DIR | wc -l`

# loop over templates
for t in `seq $NUM_TEMPLATES`; do

  # get the t'th template
  TEMPLATE=`ls $TEMPLATE_DIR | head -n $t | tail -n 1`
  TEMPLATE_FULL_PATH=$TEMPLATE_DIR/$TEMPLATE

  # create a folder for the results
  TRIAL_ID=$TEMPLATE
  TRIAL_DIR=../rijnards-experiments/$TRIAL_ID
  mkdir -p $TRIAL_DIR/repertoire

  echo "Applying $TEMPLATE_FULL_PATH" # DEBUG
  echo "---------------------" # DEBUG

  # run comby to apply the template
  comby \
  -matcher .generic \
  -sequential \
  -in-place \
  -templates $TEMPLATE_FULL_PATH &> $TRIAL_DIR/stats.txt

  # copy it for reference
  cp * $TRIAL_DIR/repertoire

  # now run the planner
  cd ..
  ./gradlew runRepertoire > rijnards-experiments/$TRIAL_ID/out.csv

  # restore repertoire to initial state
  cd repertoire
  git checkout -- .

done

# Live updates:
# stdbuf -oL tail -F rijnards-experiments/scratch/out.csv | awk -F ',' '{print $1,$4,$5}'

# Inspect values 
# T=0; cat rijnards-experiments/prune-sequence-take-first/out.csv | grep "^${T}" | awk -F ',' '{print $5}' | grep "E" | sort | tail -n 1
