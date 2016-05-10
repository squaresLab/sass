#!/bin/bash
timesToRun=10
i=0
while [ $i -lt $timesToRun ]
do
	gradle runSingleOptimization 
	mv out.stat out"$i".stat
	i=`expr $i + 1`
done
