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
    
Build all modules and docker images for them

	cd simple-multiproject
	gradlew clean build

## Start infrastructure containers

Make sure that VM has enough memory (At least 2048 Mb is recommended).
Path Docker virtual machine with command:
    
    docker-machine ssh default "sudo sysctl -w vm.max_map_count=262144"

Run containers:

    docker-compose -f docker-compose-infrastructure.yml up -d

## Start business containers
    
    docker-compose -f docker-compose-business.yml up -d

# URLs
## Business services

Through routing service:

[Wallet service swagger](http://localhost:8082/wallet-service/swagger-ui.html)

[Loyalty service swagger](http://localhost:8082/loyalty-service/swagger-ui.html)

## Eureka

[Link to UI](http://192.168.99.100:8761)
