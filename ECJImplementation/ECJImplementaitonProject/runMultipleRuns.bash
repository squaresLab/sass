#!/bin/bash
timesToRun=10
i=0
while [ $i -lt $timesToRun ]
do
	gradle runSingleOptimization > timingResults"$i".txt
	mv out.stat out"$i".stat
	i=`expr $i + 1`
done
