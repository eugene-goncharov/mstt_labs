#!/bin/bash
javac -cp libs/jade.jar src/ua/nure/mstt_labs/BookBuyerAgent.java -d ./out
cd ./out
jar cf agents.jar ua/nure/mstt_labs/*.class
cd ..
java -cp libs/jade.jar:out/agents.jar jade.Boot -agents buyer:ua.nure.mstt_labs.BookBuyerAgent