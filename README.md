# Assignment
## What?
Spring Cloud Function aimed at being deployed as a AWS Lambda function.
Once run it will retun a JSON representation of an ApaResponse.

## Dependencies
- Spring Cloud Functions
- Spring JDBC

## Execute
```
mvn spring-boot:run
```
, later you can call the function via.
```
curl localhost:8080/apaFunction -H "Content-Type: text/plain" -d "<some_id>"
```

## Test
```
mvn clean test
```

## Overall architecture
Where does this service fit in the overall architecture?
![img.png](img.png)