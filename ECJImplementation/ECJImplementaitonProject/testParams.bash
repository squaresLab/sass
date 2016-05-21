#!/bin/bash

while read file
do
	i=0
	while [ $i -lt 1 ]
	do
		outputFile=$(echo $file | cut -d. -f1)Test$i.txt
		echo "java -cp \"$PWD/libs/ecj.jar\" ec.Evolve -file $file > $outputFile"
		java -Xmx10g -cp $PWD/libs/ecj.jar:$PWD/bin ec.Evolve -file $file > $outputFile

		i=`expr $i + 1`
	done	

done << EOF
testParams/configParams1.params
testParams/configParams2.params
testParams/configParams3.params
testParams/configParams4.params
testParams/configParams5.params
testParams/configParams6.params
testParams/configParams7.params
EOF

