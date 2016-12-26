The application 'UserArticles' consists of a simple business function of user having articles. The application is built using RESTFull service and will support following API:

1. Retrieve all the articles of a user
2. Adding new article for user
3. Update existing article for a user
4. Delete an existing article for a user.  

A user could have many articles so a 1-n association exists between user & article.

###### Technology stack used:

*1. Java 1.7, Spring MVC 4.0.6.RELEASE, Spring security 4.0.0.RELEASE, Hibernate 4.2.10, Tomcat 7.0.57, MYSQL v 5.1.73, HeidiSQL*

**userarticles.sql** has been added consisting of DDL statements for the required table.


**Create a war and deploy on any servlet container, I used Tomcat. once the deployment is done successfully, use POSTMAN, ADVANCE REST or curl to test.**

How to test using curl


> Retrieve all articles for a user

HTTP METHOD : **GET**

curl command 

**curl http://HOSTNAME/{CONTEXTPATH}/api/articles -u username:password**

> Add a new article for user

HTTP METHOD : **POST**

curl command 

**curl -vX POST http://HOSTNAME/{CONTEXTPATH}/api/articles -d @c:\data.json --header "Content-Type: application/json" -u username:password**

data.json file should consists of :

{
    "title": "Head First design pattern",
    "content": "A wonderful book for beginners, experienced individuals",
    "created": "2016-12-08 18:33 PM GMT",
    "updated": null
}


> Update an existing article for a user

HTTP METHOD : **PUT**

curl command 

**curl -vX PUT http://HOSTNAME/{CONTEXTPATH}/api/articles -d @c:\data.json --header "Content-Type: application/json" -u username:password**

data.json file should consists of :

{
    "id":11,
    "title": "Head First design pattern",
    "content": "A wonderful book for beginners, experienced individuals",
    "created": "2016-12-08 18:33 PM GMT",
    "updated": null
}


> Delete an existing article for a user

HTTP METHOD : **DELETE**

curl command 

**curl -vX DELETE http://HOSTNAME/{CONTEXTPATH}/api/articles/{articleid} -u username:password**


