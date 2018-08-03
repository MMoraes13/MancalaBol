# Mancala technical assignment

This project is part of an assignment in Java for Bol.com. The design was based on the architecture suggested on the company's blog [GitHub](https://github.com/bolcom/bol-technical-assignments) account. The idea is to allow two players to play the game with the Backend written in a REST Api architecture. To allow a sweet experience and a bidirectional communication between the players and the server a websocket was the chosen solution. 

The reason to use a websocket is because Mancala is a turn based game. Therefore, the players will not notice if there is a delay in the communication between their clients and the server. If it was an action based game, another solution would be necessary such as Remote Procedure Call (RPC) protocol (i.e.eureca.io). 

A relational database was chosen due to the low complexity and low volume of the data. MySQL was the chosen one. The table scripts were written in SQL and Flyway was used to migrate the table structure and keep it versioned. 

The client side of the project was written using Angular 6, but it could be written in any of the Angular previous versions. I choose it over the others because it is very recent and it would be a challenge to implement it. Also it is supposed to be a modern, performance-efficient and powerful front-end framework. The framework brought up some new challenges, specially implementing the websocket and having trouble importing some libraries which are not ready for this new recent version.


## How to Run

MySql is defined as the database inside the folder resources at bol-technical-assignments/kalaha/src/main/resources. The datasource URL is defined and it creates the database if it doesn't existe. The only thing here to notice is that it is using the user 'root' and password '1234'. It can be changed to run the application.


To run the tests and the backend: 
```
mvn surefire:test
mvn spring-boot:run
```
It will be launched at localhost:8080

To serve the client at port 4200:

```
ng serve
```

Even though it asks for a name and a password the game was designed to be casual. So the players will be saved - there is no restriction for the name - and he/she will see the games available to join or can choose to create one.
If creating one, the other players will see the new available game in the game list  after a refresh. If joining one, the game will start and the players can play against each other. If someone wants to watch the game to learn or just to have fun, he/she just need access the address of the game (game/{id}).

## Tech stack

Backend: 

* Java 8
* Spring Boot 2.0.3
* jUnit
* MySQL
* Flyway

Frontend:

* Angular 6
* HTML 5

Tools:

* Spring tool suite
* Maven
* EclEmma

## Improvements

* Improve test coverage: according to EclEmma, the test coverage was about 50% of the code.
* Implement websocket for game-list so it doesn't need to be refreshed.
* A functional login system instead of storing the current user at the browser localstorage.
* Frontend design definetely needs to be improved.
* New functionality: a chat system.
* New functionality: a ranking system.
