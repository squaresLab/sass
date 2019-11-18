#!/bin/bash

# this script assumes that the Deckard config file is located in the same dir as
# this script

# need to give the location of Deckard
DECKARDDIR=/home/ckinneer/research/DeckardPostproc/Deckard

CLONEDETECTCMD="$DECKARDDIR/scripts/clonedetect/vertical-param-batch"

# first copy the generated vectors to the proper location

# clean up
rm vectors/*
rm clusters/*
rm times/*

cat vectorgen/* > vectors/vdb_30_0

# now run deckard to cluster
exec "$CLONEDETECTCMD"
