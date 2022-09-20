# About the project:
This project is a simple HR tool for handling both sick and annual leaves of the employees. 

# Used Technologies: 
- **Spring-Boot:** For exposing APIs and modifying the DB 
- **Angular 9:** For Web portal
- **H2** For in-memory DB so it is easy and fast to run the project 
- **Skaffld** Handles the workflow for building, pushing and deploying the application on Kubernetes

# ERD: 
![Alt text](ERD.jpg?raw=true "ERD")

# UML:
![Alt text](UML.jpg?raw=true "ERD")

# Main exposed APIs: 
- **Path:** /api/vacations-service/lookups/vacation-type

    - **Method:** Get 

    - **Description:** this method is responsible for getting All the available leaves types
    - **Response Example:** `{"data":[{"type":"ANNUAL"},{"type":"SICK"}]}`
#    
- **Path:** /api/vacations-service/employee ,

    - **Method:** Get

    - **Description:** this method is responsible for getting details of current user like his name and his leaves balance.
      _note : current user is mocked to be always user with id 1_.
    - **Response Example:** `{"data":{"name":"Naguib","annualVacationBalance":21,"sickVacationBalance":15,"vacations":[{"id":1,"vacationStartDate":"2022-09-13","vacationEndDate":"2022-09-15","numberOfDays":3,"vacationTypeEntity":{"type":"SICK"}}]}}`
#
- **Path:** /api/vacations-service/vacation/duration?startDate=2022-09-20&endDate=2022-09-20
 
     - **Method:** Get 
 
     - **Description:** this method is responsible for calumniation the number of days that will be considered as leave
                          excluding the weekend and the official holidays.
     - **Response Example:** `{"data":1}`

#
- **Path:** /api/vacations-service/vacation 
     - **Method:** POST 
 
     - **Description:** this method is responsible submitting the employee's leave then update his leaves balance,also it will validate the input data to make sure
                        for example the leave end date will be after the start date ,there is sufficient balance for the user, and the 
                        there is no intersections between the leaves.
     - **Request body Example:** `{"vacationStartDate": "2022-11-03","vacationEndDate": "2022-11-06","vacationType": "SICK"}`
     - **Response Example:** `{"data":{"id":3,"vacationStartDate":"2022-11-03","vacationEndDate":"2022-11-06","numberOfDays":4,"vacationTypeEntity":{"type":"SICK"}}}`
     - **Response Example:** `{"errorMessage":"Start-date is after end-date","errorCode":"vacations-service-410"}` **with status code 400**
     - **Response Example:** `{"errorMessage":"No sufficient balance","errorCode":"vacations-service-412"}` **with status code 400**
     - **Response Example:** `{"errorMessage":"Someday are already submitted","errorCode":"vacations-service-413"}` **with status code 400**

# Web View:

![Alt text](webViewScreenShoots/1.png?raw=true "1")
![Alt text](webViewScreenShoots/2.png?raw=true "2")
![Alt text](webViewScreenShoots/3.png?raw=true "3")


# Putting Project on rails:
As the project is configured with Skaffold we can simply run "skaffold run" and it will run both back-end and front-end service,
if we do not have Skaffold installed we can run each project separately 
