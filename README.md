**Action Monitor**
-

Action Monitor Spring Boot application using Spring WebSockets and H2 Database.

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
Then you have to open http://localhost:8080 and enter your username then click on button.
From here you can start sending messages, and you will be notified when a new one was received.
In order to better test this you can open more than one tab, and you can chat from one tab to the other.

Logs & DB
---------
Every log files are located in the directory where you started from the application/tomcat/logs.
In the same directory from where you started from the app.

Testing
-------
To run test simply go into the project directory and type:
```
mvn test
```
The testing approach that was used in this project is a combination of Mockist and Classic.

Endpoints
---------
http://localhost:8080          -> Chat application
http://localhost:8080/status   -> Check application status
http://localhost:8080/version  -> Check application version
http://localhost:8080/all      -> Retrieve all messages from DB

For Testing purposes:
http://localhost:8080/message  -> Send Post Messages
{
    "content": "test message",
    "sender": "username",
    "time": "date",
	"type": "CHAT"
}