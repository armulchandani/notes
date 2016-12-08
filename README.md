The application 'notes' consists of a simple business function of user having notes. The application is built using RESTFull service and will support following API:

1. Retrieve all the notes of a user
2. Adding new note for user
3. Update existing note for a user
4. Delete an existing note for a user.  

A user could have many notes so a 1-n association exists between user & note.

###### Technology stack used:

*1. Java 1.7, Spring MVC 4.0.6.RELEASE, Hibernate 4.2.10, Tomcat 7.0.57, MYSQL v 5.1.73,HeidiSQL*

**usernotes.sql** has been added consisting of DDL statements for the required table.


**Create a war and deploy on any servlet container, I used Tomcat. once the deployment is done successfully, use POSTMAN, ADVANCE REST or curl to test.**

How to test using curl


> Retrieve all notes for a user

HTTP METHOD : **GET**

curl command 

**curl http://HOSTNAME/usernotes/user/{userid}/notes**

> Add a new note for user

HTTP METHOD : **POST**

curl command 

**curl -vX POST http://HOSTNAME/usernotes/user/{userid}/notes -d @c:\data.json --header "Content-Type: application/json"**

data.json file should consists of :

{
    "title": "Head First design pattern",
    "note": "A wonderful book for begineers, experienced individuals",
    "created": "2016-12-08 18:33 PM GMT",
    "updated": null
}


> Update an existing note for a user

HTTP METHOD : **PUT**

curl command 

**curl -vX PUT http://HOSTNAME/usernotes/user/{userid}/notes -d @c:\data.json --header "Content-Type: application/json"**

data.json file should consists of :

{
    "title": "Head First design pattern",
    "note": "A wonderful book for begineers, experienced individuals",
    "created": "2016-12-08 18:33 PM GMT",
    "updated": null
}


> Delete an existing note for a user

HTTP METHOD : **DELETE**

curl command 

**curl -vX DELETE http://HOSTNAME/usernotes/user/notes/{userid}***


