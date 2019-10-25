#!/bin/bash

TEMPLATE_DIR=$1

VARIANT_ITERATIONS=10
NUM_TEMPLATES=`ls $TEMPLATE_DIR | wc -l`

INITIAL_INPUT=`cat`
# echo "INITIAL INPUT: $INPUT" # DEBUG

# echo "Doing $VARIANT_ITERATIONS variant iterations" # DEBUG

# HOW MANY VARIANTS WE'LL GENERATE (10)
for v in `seq $VARIANT_ITERATIONS`; do
  # HOW MANY TEMPLATES WE WILL APPLY (range 1 to 5)
  TEMPLATE_ITERATIONS=$((`expr $RANDOM % 5` + 1))
  INPUT=$INITIAL_INPUT

  # UUID=`uuidgen`
  # TARGET_DIR=variant-$UUID
  # cp -r seed $TARGET_DIR

# echo "-=-=-=-=-=-=-=-=" # DEBUG
# echo "$v: applying $TEMPLATE_ITERATIONS templates" # DEBUG
# echo "-=-=-=-=-=-=-=-=" # DEBUG
  for i in `seq 1 $TEMPLATE_ITERATIONS`; do
    TEMPLATE_NUM=$((`expr $RANDOM % $NUM_TEMPLATES` + 1))
    TEMPLATE=`ls $TEMPLATE_DIR | head -n $TEMPLATE_NUM | tail -n 1`
    TEMPLATE_FULL_PATH=$TEMPLATE_DIR/$TEMPLATE
#   echo "Applying $TEMPLATE_FULL_PATH" # DEBUG
#   echo "---------------------" # DEBUG
	INPUT=$(echo "$INPUT" | \
    comby \
    -matcher .generic \
    -sequential \
    -stdin \
    -stdout \
    -templates $TEMPLATE_FULL_PATH)
#   echo "OUTPUT: $INPUT" # DEBUG
  done
  echo $INPUT
# echo "GENERATING NEXT VARIANT"
done
