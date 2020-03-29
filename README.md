# Getting started
Application developed using Service Layer architecture including DI and other Design Patterns.
Initially all chargers are stored in MongoDB database, to be able to retrieve geolocation based queries very fast, because
it provides high level Geospatial API.
 
## Prerequisites

- Running in docker
  - `Docker Desktop (Mac, Windows)`
  - `Docker Compose`
- Running locally
  - `jdk-1.8`
  - `MongoDB`
  
 To get up it running use `docker-compose up` or `./gradlew bootRun`
 
 ```bash
 docker-compose up
 ```

See endpoints

- Create new charger
```bash
curl --request POST \
  --url http://localhost:8090/api/v1/chargers/create \
  --header 'content-type: application/json' \
  --data '{
    "id": "c91f9999-5834-4f92-8683-d88a2fde0fcf",
    "postalCode": "54321",
    "location": {
      "longitude": 38.9024593,
      "latitude": -69.0388266
    }
}'
```

- Charger info by ID
```bash
curl --request GET \
  --url http://localhost:8090/api/v1/chargers/c91f9999-5834-4f92-8683-d88a2fde0fcf
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

To running tests use:
```bash
sh test.sh & sh qa.sh 
```

Quality Assurance  
- `Sonar Qube` reports can be found here: [SonarCloud](https://sonarcloud.io/dashboard?id=destination-charging)
- Coverage results can be found here: `./build/reports/jacoco/test/html/index.html`