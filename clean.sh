#!/bin/bash
find . -name target -type d -exec rm -f {} \;
sbt clean clean-files
