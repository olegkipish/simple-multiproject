# Simple multiproject

It contains modules:
* Wallet service  - create players and store payment deposits
* Loyalty service - assign loyalty points to players

To clone, build and start application you need:
* Git client
* Java 8

# Build and start

Clone project

    git clone https://github.com/olegkipish/simple-multiproject.git    
    
Build all modules

	cd simple-multiproject
	gradlew clean build

## Start loyalty service

	cd loyalty-service\build\libs
	java -jar loyalty-service-0.0.1-SNAPSHOT.jar
	
## Start wallet service

	cd wallet-service\build\libs
	java -jar wallet-service-0.0.1-SNAPSHOT.jar

# URLs
## Business services

[Wallet service swagger](http://localhost:8090/wallet-service/swagger-ui.html)

[Loyalty service swagger](http://localhost:8080/loyalty-service/swagger-ui.html)