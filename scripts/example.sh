#!/bin/bash

cd ../repertoireBuilder
time ../scripts/fixpoint.sh ../scripts/1-group-seq.sh
time ../scripts/fixpoint.sh ../scripts/2-reduce-seq.sh
