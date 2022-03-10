# bad-words-api

IntelliJ was used while developing this maven project, using Spring/Spring Boot 2.x and Java 8

When cloning this project and opening in the IDE, please make sure that the maven dependencies are imported, when that is done you may run the project
***
##NB: I made use of the LOMBOK plugin to save myself time from unnecessary creation of getters and setters in my POJOs. Please install that plugin if it doesn't pull through from the **pom.xml** file when importing maven dependencies  
***

When running this project the default host will be  **http://localhost:8080** 

*When the project is in runtime;*
<br />There are three end points (APIs) that has their unique request and response bodies with a ***Content-Type: application/json*** header when making use of these APIs
***
***

### *Example if no bad word is used:*

POST
<br />**localhost:8080/words/v1/find**

Request body:
<br />**{ "requestMessage": "I like you" }**

Response body:
<br />**{ "headers": {}, "body": { "responseMessage": "Looks like you have some manners -_'" }, "statusCode": "OK", "statusCodeValue": 200 }**
***



### *Example if bad word is used:*

POST
<br />**localhost:8080/words/v1/find**

Request body:
<br />**{ "requestMessage": "Me ?"}**

Response body:
<br />**{ "headers": {}, "body": { "responseMessage": "This is a bad word, stop it with your profanity! -_-. Word used: (?) " }, "statusCode": "OK", "statusCodeValue": 200 }**
***



