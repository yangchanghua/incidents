# incidents
Incident management

# Get started

## Start postgres

```bash
./postgres/start.sh
```

To check and connect to the started postgres database, execute the command:

```bash
./postgres/psql.sh
```

Then you can query the sample data using below SQL:

```sql
mydatabase=# select * from users;
 id |  name   |        email
----+---------+---------------------
  1 | Alice   | alice@example.com
  2 | Bob     | bob@example.com
  3 | Charlie | charlie@example.com
(3 rows)
```

## Build the spring-boot application

Open another bash window, go to the project folder and execute the commands:

```bash
cd incident-backend

mvn clean install
```

## Start the spring-boot application

```bash
mvn spring-boot:run
```

## Run E2E tests

Open another bash window, go to the project folder and execute the commands:

```bash
cd incident-backend

./run_karate.sh
```

## Open API documentation

After server started, open http://localhost:8080/swagger-ui/index.html to check the Open API doc.


# Dependencies used

## lombok

For easier Data model definition.

## swagger-annotations
Annotations used for documenting Open APIs.

## springdoc-openapi-starter-webmvc-ui
spring-boot starter for supporting Open API doc in spring-boot project.

## karate-core and karate-junit5
For writing automated integration tests.