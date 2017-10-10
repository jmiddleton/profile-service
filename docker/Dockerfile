FROM openjdk:8-jre-alpine

# Add application jar
ADD /profile-service.jar /

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /profile-service.jar