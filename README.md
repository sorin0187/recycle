# recycle Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Structure
This project has 3 components
 - database - a Postgresql DB that stores all the information about the recycled objects and vouchers
 - backend - a backend REST service built using quarkus
 - frontend - a react.js fronted

## Build the project
```
mvn clean package
docker-compose build
docker-compose up
```
After all services are up, open a browser and navigate to ```http://localhost:3000```

## Postman
For the backend service there is also a postman collection with sample requests in */src/test/postman*.
