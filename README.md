# Profile Service #

Profile Service REST API allows customers to create, retrieve, delete or update member details. 
This API can be used by any client that support OAuth 2.0 security model.

### What is this repository for? ###

This repository demostrates a simple Spring Boot application that exposes an API to manage user Profile. Currently, the information is stored in memory but the code is extensible to any backend system.

Version 0.0.1

### How do I get started? ###

* Clone the repo: `git clone https://github.com/jmiddleton/profile-service.git`

* `cd profile-service`

* To build the application execute `mvn clean install`

* To execute the application run `java -jar -Djava.security.egd=file:/dev/./urandom -jar target/profile-service.jar`

* Open Postman, import the collection `src/test/resources/Profile-Service.postman_collection` and run the tests as follows:


1. Create: Create a profile and get the profileId
1. Get: Using profileId, get the profile details
1. Update: Update the profile
1. Get: Retrieve the new profile using profileId
1. Delete: Delete the profileId
