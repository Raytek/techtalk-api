# techtalk-api
> Demo project designed for use as an example in tech talks
### How to manually build the database?
`docker run --volume ~/Git/techtalk-api/src/integrationTest/resources/schema.sql:/docker-entrypoint-initdb.d/10-schema.sql --volume ~/Git/techtalk-api/src/integrationTest/resources/data.sql:/docker-entrypoint-initdb.d/20-data.sql -p 5432:5432 --env POSTGRES_PASSWORD=techtalk --name postgres --detach postgres:10.5-alpine`
- `[PATH]` - Change for your local path
### How to manually run the application?
Inside of the project folder run:
1. `./gradlew clean build`
1. `./gradlew bootRun`
### Request example:
`curl localhost:8383/person/1`