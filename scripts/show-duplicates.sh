#!/bin/bash

ls Plan* | xargs -L 1 -I % bash -c 'echo "" >> %' && cat Plan* | sort > /tmp/plans.txt
ls Plan* | xargs -L 1 -I % bash -c 'echo "" >> %' && cat Plan* | sort -u > /tmp/deduped-plans.txt

diff /tmp/{plans,deduped-plans}.txt
DUPES=$(diff /tmp/{plans,deduped-plans}.txt | grep "^<" | wc -l)
echo "$DUPES duplicates"

git checkout -- .
