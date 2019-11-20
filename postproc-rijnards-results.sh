#!/bin/bash

# this script aggregates the result of rijnards experements
# it should be run from inside the rijnards-experements directory
# output will be saved in a file called postproc.csv

# loop over all of the out.csv files
for f in `find . -name 'out.csv'`
do
   # extract the id from the parent directory name
   id=`echo $f|awk -F'/' '{SL = NF-1; print $SL}'`
   # append the id to the output file, and filter out garbage output lines
   # (i.e., gradle messages, headers, and stacktraces)
   sed -e "s/$/,!!$id!!/" "$f" | grep "\"" | sed -e "s/!!/\"/g" > "$f.postproc.csv"
done

# aggregate the results into one big file, and replace the header
HEADER="trial,generation,bestSize,runtime,profit,distance,structureDistance,plan,init,window,buildProb,runtimeKill,trimmerChance,scenario,averageSize,scenarioMutations,transformID"

echo $HEADER > postproc.csv
find . -name 'out.csv.postproc.csv' -exec cat {} \; >> postproc.csv
