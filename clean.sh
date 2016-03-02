#!/bin/bash
find . -name target -type d -exec rm -f -r {} \;
sbt clean clean-files
