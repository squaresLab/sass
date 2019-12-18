#!/bin/bash

# This is the relative rijnard uses when running ./minimize.sh in the repertoire dir.
PATH_TO_MAIN=../normalize/main

rm *.reduced

for f in `ls *.txt`; do cat $f | comby ';' '";"' -stdin -stdout -matcher .generic | $PATH_TO_MAIN > $f.reduced; done

ls *.reduced | xargs -L 1 -I % bash -c 'echo "" >> %'

cat *.reduced | sort -u | wc -l

# add a space, because the original files have a space
ls *.reduced | xargs -L 1 -I % bash -c 'sed -i .orig "s/^/ /" %'

# add a newline so we can diff, because the original files don't have a newline. we need a newline from before to sort
ls *.txt | xargs -L 1 -I % bash -c 'echo "" >> %'

for f in `ls *.txt`; do diff $f $f.reduced; done

rm *.orig
