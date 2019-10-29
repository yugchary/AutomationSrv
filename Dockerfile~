FROM openjdk:8-jre-slim

RUN apt-get update && apt-get install -y vim

RUN apt-get install -y curl

RUN apt-get install -y jq

RUN apt-get install -y iputils-ping

RUN apt-get install -y libfreetype6-dev

RUN apt-get install -y libfontconfig

RUN export JAVA_OPTS="-Xmx12g -Xms12g"

WORKDIR /usr/share/tag

# Add the project jar & copy dependencies

ADD  target/libs libs
ADD  src/main/java/com/lco/qa/config config
ADD  src/main/java/com/lco/qa/testdata testdata

RUN chmod -R 777 /usr/share/tag/testdata
ADD  selenium_libs selenium_libs

ADD  target/selenium-LCO-docker.jar libs/selenium-LCO-docker.jar
ADD  target/selenium-LCO-docker-tests.jar libs/selenium-LCO-docker-tests.jar

#ADD  selenium_libs/chromedriver chromedriver
#ADD  selenium_libs/geckodriver geckodrivera	

ADD  src/main/resources/testNG_Sanity_lco.xml testNG_Sanity_lco.xml

#ADD  src/main/java/com/lco/qa/config/config.properties_libs/config.properties

ADD healthcheck.sh healthcheck.sh
# Add the suite xmls




# Command line to execute the test
# Expects below ennvironment variables
# BROWSER = chrome / firefox
# MODULE  = testNG_Sanity_lco.xml
# GRIDHOST = selenium hub hostname / ipaddress

# ENTRYPOINT java -cp libs/* -DseleniumHubHost=$SELENIUM_HUB -Dbrowser=$BROWSER org.testng.TestNG $MODULE

ENTRYPOINT sh healthcheck.sh

#VOLUME ["/testdata"]


#COPY /usr/share/tag/testdata /home/user/customer-module-result


