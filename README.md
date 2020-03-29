# Getting started
Application developed using Service Layer architecture including DI and other Design Patterns.
Initially all chargers are stored in MongoDB database, to be able to retrieve geolocation based queries very fast, because
it provides very good high level Geospatial API.
 
## Prerequisites

- Running in docker
  - Docker Desktop (Mac, Windows)
  - Docker Compose
- Running locally
  - JDK 8
  - MongoDB
  - bash
  
 To get up it running use `docker-compose up` or `./gradlew bootRun`
 
 ```bash
 docker-compose up
 ```

See endpoints

- Charger info by ID
```bash
curl --request GET \
  --url http://localhost:8090/api/v1/chargers/c6d11464-d396-4f5c-a269-749826b6e30e
```

- Chargers by Postal Code
```bash
curl --request GET \
  --url 'http://localhost:8090/api/v1/chargers/getByPostalCode?postalCode=435443'
```
- Chargers by location within actual radius
```bash
curl --request GET \
  --url 'http://localhost:8090/api/v1/chargers/getByLocationWithinRadius?long=38.8993487&lat=-77.0145665&radius=10'
``` 

To running tests simple use 
```bash
sh test.sh & sh qa.sh 
```

Quality Assurance  
- Sonar Qube reports can be found here: [SonarCloud reports](https://sonarcloud.io/dashboard?id=destination-charging)
- Coverage results can be found here: ./build/reports/jacoco/test/html/index.html