# FlightSystemDemo

FlightSystemDemo is a project developed as a test task for Company YY. It is a system designed to manage airports, passengers, and flights, providing various functionalities for manipulation and organization.

Features
1) Airport Management: Create, update, and delete airports with detailed information such as name, location, and code.
2)Passenger Management: Add, edit, and remove passengers from the system, including personal details and contact information.
3) Flight Management: Manage flights, including scheduling, seat availability, and destinations.
4) Flight Search: Search for flights based on criteria such as airports on path, date, and available seats.
5) Flight Booking: Allow passengers to book seats on flights.

## Main Technologies Used
1) Java: The backend of the application is developed using Java, providing robustness and flexibility.
2) Spring Boot: FlightSystemDemo is built with Spring Boot, enabling rapid development and easy integration of various components.
3) PostgreSQL: The project uses PostgreSQL as the database management system for storing and managing data related to airports, passengers, and flights.
4) Hibernate: Hibernate is utilized for object-relational mapping (ORM), simplifying database interactions and providing seamless integration with Spring Boot.

## How to test?

To test the app you need to:
1) Download repo using button Code/Download as Zip and then unpack it, or you can clone project.
2) From the main application directory(From which you can see Dockerfile) open terminal and use "docker-compose up".
3) Using Postman(https://www.postman.com/downloads/) or every other tool for performing HTTP request open postman-collection that is located in the specified folder in the app directory.
   There are 10 airports and 10 passengers already created in the Database for testing purposes, but THERE ARE NO FLIGHTS in the database, make sure to create them if you want to test this part of
   functionality. Also, combining filter parameters (minSeats, Airport and any other combination) is possible, but there is no request for each combination.


## What Can Be Done Later
1) User Authentication and Authorization: Currently, the system is designed to be managed by flight company workers without the need for individual user accounts. However, implementing user authentication and authorization functionality would enhance security and allow for role-based access control. This would enable different levels of access for administrators, employees, and possibly passengers.
2) Unit Testing: Unit testing is essential for ensuring the reliability and stability of the application. While the core functionalities are implemented, writing unit tests for each component and functionality would help in identifying and preventing regressions or bugs in the future. Implementing unit tests would also make it easier to refactor and extend the codebase without introducing unintended side effects.
3) Integration Testing: In addition to unit tests, integration tests can be added to validate the interactions between different components of the system. Integration testing would ensure that various modules work together seamlessly and that the system behaves as expected under different scenarios.
