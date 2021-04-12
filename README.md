# one-project
-----
## Description

SI for smartbuildings
An application that allows you to rent one or more rooms among the 5 smartbuildings of the city, and manage them as you wish (light, alarms, amenities...).

-----
## Modules


### Server

The backend part of the project which concerns the **server** managing client's sockets and the **postgresql database**. The server is running on the vm xana while the database is supported on the venom vm. The server accepts multi-threading connections and deals with the database to answer the clients' requests. For now, the client is sending a list things to insert in the database with a *json file* and the server calls the method to add those elements in the database. The server supports a defined number of connections given by the administrator.
A script as been written to launch the server.
A yaml file named serverConfig.yaml is the config file of the server (port, maxConnections) databaseConnection.yaml is the config file to access the database (url, driver, user, password).

### Client
 
The frontend part of the project which concerns the client **tcp connection** and the **GUI application**. The client uses a socket tcp connection to access to the database. The list of data he wants to enter in the database is located in the jsonformatter.json file. The application shows to the user all the rooms rented by his company. If he needs new rooms, he has to click on the "Louer des salles" section and has to enter some search criteria. With those criteria, the SI will propose him the best available offers. All he'll have to do is choose one.
A script as been written to create a client.
A yaml file named socketConnection.yaml is the config file of the socket connection (ip, port).
