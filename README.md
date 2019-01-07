# TestBackJava README file

This application is built using Spring Boot setup to work with Redis.
**Redis is supposed to be running on `localhost:6379`**
For your convenience, an embedded server is being used for the test cases.

## Running the application
You can via Maven, using the command:
```
$ mvn spring-boot:run
```  
Or, via jar:

```
$ mvn package
$ java -jar target/testbackjava-0.0.1-SNAPSHOT.jar
```

