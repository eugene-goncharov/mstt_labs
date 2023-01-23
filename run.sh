#!/bin/bash

find ./src -type f -name "*.java" > sources.txt
javac -cp libs/jade.jar -d ./out @sources.txt
cd ./out
jar cvf agents.jar ua/nure/mstt_labs/
cd ..
java -cp libs/jade.jar:out/agents.jar jade.Boot -agents seller:ua.nure.mstt_labs.BookSellerAgent\;buyer:ua.nure.mstt_labs.BookBuyerAgent\("Prisoner of Azkaban"\)