**Action Monitor**
-

Action Monitor Spring Boot application using ActiveMq and Spring WebSocket

Description
-----------------------
This application is a PoC to allow two users to communicate between themselves pushing the messages to the destination user. All the messages
should be stored in a database.
The database is H2 Database.
This table simply contains text messages.
There are some REST endpoints for application health check and manual testing.
This is a standalone Spring Boot based application.

Build & Run
-----------
First of all go to the project's directory and run the following command from the command line:
```
mvn clean package
```
Then type:
```
java -jar target/actionmonitor-1.0.jar
```
How it works
------------
When the application starts, the database will be created.
Then you have to open http://localhost:8080 and click on "Connect" button.
From here you can start sending messages, and you will be notified when a new one was received.
In order to better test this you can open more than one tab, and you can chat from one tab to the other.

Logs & DB
---------
Every log files are located in the directory where you started from the application/tomcat/logs.
In the same directory (where you started from the app) there you can find the database file also.

Testing
-------
To run test simply go into the project directory and type:
```
mvn test
```

The testing approach that was used in this project is a combination of Mockist and Classic.