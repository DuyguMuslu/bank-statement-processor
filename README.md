BANK STATEMENT PROCESSOR APPLICATION
----------------------------------------
This is a simple service for parsing statement record files sent by clients, persist statements in Data store and displaying consolidated data.

Build
-----
- Clone the project to your local environment
(H2 Embedded DB is used)
- In the project directory run `./mvnw spring-boot:run`
 
Usage
---------------
- Base Path for the application is `http://localhost/api/v1`
- REST APIs can be found on following URL 
  http://localhost:8080/api/v1/upload
- Sample requests are listed on a postman collection
        https://www.getpostman.com/collections/d21ef5492542bd1d2402
        __Download__ : https://www.postman.com 
- DB Console can be accessed via http://localhost:8080/api/v1/h2-console/login.jsp
    .No password required, JDBC Url is `jdbc:h2:file:~/statement-schema;AUTO_SERVER=TRUE` 
    
