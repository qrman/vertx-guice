

## Sample usage of Vert.x 3 with Guice DI

To compile use:
```
mvn clean package
```

To run use:
```
java -jar target/guice-verticle-1.0-SNAPSHOT-fat.jar -conf config.json
```

To verify use:

```
curl -w " will win Wimbledon in 2015\n" -X GET http://localhost:8080/wimbledon/winner
```

Then you should see the answer.
