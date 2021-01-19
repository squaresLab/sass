#!/bin/bash
timesToRun=10
i=0
while [ $i -lt $timesToRun ]
do
	gradle runCoev > out"$i".csv &
	#mv out.stat out"$i".stat
	i=`expr $i + 1`
done
