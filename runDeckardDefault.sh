#!/bin/bash

# this script assumes that the Deckard config file is located in the same dir as
# this script

# need to give the location of Deckard
DECKARDDIR=/home/ckinneer/research/DeckardPostproc/Deckard

CLONEDETECTCMD="$DECKARDDIR/scripts/clonedetect/deckard.sh"

# first copy the generated vectors to the proper location

# clean up
rm vectors/*
rm clusters/*
rm times/*

# now run deckard to cluster
exec "$CLONEDETECTCMD"
