#!/bin/bash

# this script assumes that the Deckard config file is located in the same dir as
# this script

# need to give the location of Deckard
DECKARDDIR=/home/cody/repertoirework/Deckard

CLONEDETECTCMD="$DECKARDDIR/scripts/clonedetect/deckard.sh"

# clean up
rm vectors/*
rm clusters/*
rm times/*

# now run deckard to cluster
exec "$CLONEDETECTCMD"
