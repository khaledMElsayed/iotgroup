Description
A shop in London has 2 million IoT tracking devices in the warehouse for sale, of which half needs configured to meet UK standards.


Software Requirements

Programming language: Java 8.

Framework: Spring Boot.

Database: Mongo database.

Automated build: Apache Maven.



Notes: 
You need First to install **Mongo database**.
You can find unit testing in **IoTControllerTest** class file.


To run the project, do the steps below:

Steps

For Windows OS:

	* Execute build.bat file to build the project
	
	* Execute run.bat file to run the project
	
For Linux OS:

	* Execute build.sh file to build the project
	
	* Execute run.sh file to run the project
	


* GET Method API to retrieve all Waiting for activation documents:


Request example:

http://localhost:8080/v1/VFIoT/getAllWaitingForActivation

Response example:

   {
        "id": "61111b0cde0a3318de6a2a48",
        "simId": "01010101010",
        "operatorCode": "3456",
        "country": "Italy",
        "simStatus": "Waiting for activation",
        "deviceName": "d-123-89",
        "deviceStatus": "READY",
        "temperature": "25'C"
    }


* GET Method API to retrieve all Ready Devices:


Request example:

http://localhost:8080/v1/VFIoT/getAllReadyDevices

Response example:

   {
        "id": "61111b0cde0a3318de6a2a48",
        "simId": "01010101010",
        "operatorCode": "3456",
        "country": "Italy",
        "simStatus": "Waiting for activation",
        "deviceName": "d-123-89",
        "deviceStatus": "READY",
        "temperature": "25'C"
    }


* Post Method API to Add Document:


Request example:

http://localhost:8080/v1/VFIoT/device

Request body:

   {
        "id": "61111b0cde0a3318de6a2a48",
        "simId": "01010101010",
        "operatorCode": "3456",
        "country": "Italy",
        "simStatus": "Waiting for activation",
        "deviceName": "d-123-89",
        "deviceStatus": "READY",
        "temperature": "25'C"
    }




* PUT Method API to Update Document:


Request example:

http://localhost:8080/v1/VFIoT/device/{managerKey}


Request body:

   {
        "simId": "01010101010",
        "operatorCode": "3456",
        "country": "Italy",
        "simStatus": "Waiting for activation",
        "deviceName": "d-123-89",
        "deviceStatus": "READY",
        "temperature": "77'C"
    }

* PUT Method API to Update Document:


Request example:

http://localhost:8080/v1/VFIoT/device/{simId}/{operatorCode}/{managerKey}



* DELETE Method API to delete Document:


Request example:

http://localhost:8080/v1/VFIoT/device/{simId}/{operatorCode}/{managerKey}




