#!/bin/bash

TEMPLATE_DIR=$1

ITERATIONS=1 # Keep this at 1 always
MAX_NUM_TEMPLATE_APPLICATIONS=5
NUM_TEMPLATES=`ls $TEMPLATE_DIR | wc -l`

for v in `seq $ITERATIONS`; do
  # HOW MANY TEMPLATES WE WILL APPLY (range 1 to 5)
  TEMPLATE_ITERATIONS=$((`expr $RANDOM % $MAX_NUM_TEMPLATE_APPLICATIONS` + 1))

  # UUID=`uuidgen`
  # TARGET_DIR=variant-$UUID
  # cp -r seed $TARGET_DIR

  echo "-=-=-=-=-=-=-=-=" # DEBUG
  echo "$v: applying $TEMPLATE_ITERATIONS templates" # DEBUG
  echo "-=-=-=-=-=-=-=-=" # DEBUG
  for i in `seq 1 $TEMPLATE_ITERATIONS`; do
    TEMPLATE_NUM=$((`expr $RANDOM % $NUM_TEMPLATES` + 1))
    TEMPLATE=`ls $TEMPLATE_DIR | head -n $TEMPLATE_NUM | tail -n 1`
    TEMPLATE_FULL_PATH=$TEMPLATE_DIR/$TEMPLATE
    echo "Applying $TEMPLATE_FULL_PATH" # DEBUG
    echo "---------------------" # DEBUG
    comby \
    -matcher .generic \
    -sequential \
    -in-place \
    -templates $TEMPLATE_FULL_PATH
  done
  echo "GENERATING NEXT VARIANT"
done
