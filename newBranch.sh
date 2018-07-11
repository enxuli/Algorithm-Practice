#!/bin/sh

clear
git checkout master
git pull origin master
git checkout -b "$*"


