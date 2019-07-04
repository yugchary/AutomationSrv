#!/usr/bin/env bash
# Environment Variables
# HUB_HOST
# BROWSER
# MODULE

HUB_HOST="selenium-hub"
BROWSER="firefox"
MODULE="testNG_Sanity_lco.xml"
JAVA_OPTS="-Xmx3g -Xms3g"

echo "Checking if hub is ready - $HUB_HOST"
echo "$( curl -s http://$HUB_HOST:4444/wd/hub/status | jq -r .value.ready )"

while [ "$( curl -s http://$HUB_HOST:4444/wd/hub/status | jq -r .value.ready )" != "true" ]
do
       echo "in sleeping"
	sleep 2  
        echo "sleeping"
done

pwd

# start the java command
java $JAVA_OPTS -cp "libs/*" org.testng.TestNG testNG_Sanity_lco.xml

#java -cp libs/* \
#    -DHUB_HOST=$HUB_HOST \
#    -DBROWSER=$BROWSER \
#    org.testng.TestNG $MODULE
    