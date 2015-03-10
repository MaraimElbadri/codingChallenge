#!/bin/sh
cd "$(dirname "$0")"
javac  javac Main.java WordCount.java RunningMedian.java  -d ../bin/
java   Main "wc_input/test.txt"
java RunningMedian "wc_input" "wc_output"

exit 0                        # Indicate clean exit