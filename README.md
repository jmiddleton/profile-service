# Profile Service #

Profile Service REST API allows customers to create, retrieve, delete or update member details. 
This API can be used by any client that support OAuth 2.0 security model.

### What is this repository for? ###

* Quick summary
Simple Spring Boot application that exposes an API to manage user Profile. The information is stored in memory.

* Version
0.0.1

### How do I get set up? ###

* Summary of set up

Clone the repo: https://github.com/jmiddleton/profile-service.git

`cd profile-service`

* Building the application

To build the application execute `mvn clean install`

* Execution

To execute the application run `java -jar -Djava.security.egd=file:/dev/./urandom -jar target/profile-service.jar`

* Postman script

Open Postman and import the collection src/test/resources/Profile-Service.postman_collection.
Runs the tests in the following order:

* Create: Create a profile and get the profileId
* Get: Using profileId, get the profile details
* Update: Update the profile
* Get: Retrieve the new profile using profileId
* Delete: Delete the profileId
