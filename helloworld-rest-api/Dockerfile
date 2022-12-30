#Start with a base image containing java runtime.
FROM openjdk:17

#Information around who maintains the image
MAINTAINER sarathchandrach

#Add the applicaiton's jar to the container
COPY target/helloworld-rest-api-0.0.1-SNAPSHOT.jar helloworld-rest-api-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/helloworld-rest-api-0.0.1-SNAPSHOT.jar"]