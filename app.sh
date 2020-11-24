#!/bin/sh

if [ $1 = "run" ]; then
	## Criando o arquivo .jar da aplicação
	./gradlew clean build
	cd build/libs
	echo "Running Data Analysis"
	##Rodando o .jar da app
	java -jar analysis.jar
fi