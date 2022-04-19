# books-managment-system
Lets you manage your books
Book management system is a simple web application that demostartes the CRUD operations using Spring Boot, Hibernate and MySQL.

Requirements
Java - 17

Apache Maven 3.8.5

Spring Boot - 2.6.6 - With Embedded Tomcat, Maven, H2 in memory Database, JPA-Hibernate

Swagger2, Swagger-UI  - 2.9.2 for API documentation

JUnit, RestAssured, Hamcrest for API testing

Getting Started
1. Clone the application

git clone https://github.com/coder-rocks/book-management-system.git
 
2. Build and run the app using maven

Run the spring boot app by typing the following command -

mvn spring-boot:run

You can also package the application in the form of a jar file and then run it like so -

mvn package
java -jar target/book-0.0.1-SNAPSHOT.jar

The app will start running at http://localhost:8080/.
# Spring boot, Hibernate, MySQL REST API

A simple Spring boot application that demonstrates the usage of RESTful API using Spring boot, Hibernate and MySQL. 

## Tools and Technologies used

* Java - 17
* Apache Maven 3.8.5
* Spring Boot - 2.6.6 - With Embedded Tomcat, Maven, H2 in memory Database, JPA-Hibernate
* Swagger2, Swagger-UI  - 2.9.2 for API documentation
* JUnit, RestAssured, Hamcrest for API testing

## Steps to install

**1. Clone the application**

```bash
git clone https://github.com/coder-rocks/book-management-system.git
```

**2. Create  table**

```
Schema will be created on startup in memory database using schema.sql present at src/main/resources
```
	
**3. Run the SQL script file**

```sql
Book entries will be created on startup in memory database using data.sql present at src/main/resources
```
	
**4. Build the app**
	
You can also package the application in the form of a `jar` file and then run it like so -

```bash
mvn package
java -jar target/book-0.0.1-SNAPSHOT.jar
```

**5. Run the app**

You can also run the spring boot app by typing the following command -

```bash
mvn spring-boot:run
```

The server will start on port 8080.
	
## 6. API Documentation using Swagger - http://localhost:8080/swagger-ui.html

The app defines following CRUD APIs.

    GET /book
    
    POST /book
    
    GET /book/{isbn}
    
    PUT /book/{isbn}
    
    DELETE /book/{isbn}
    
    POST /book/import
    
    DELETE /book/search
