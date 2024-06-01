# Non-Revenue Water Management System

## Introduction:-
The Non-Revenue Water Management System is designed to help water utilities monitor, detect, and manage water losses effectively. Non-Revenue Water (NRW) refers to water that is produced but not billed to customers due to various reasons such as leaks, theft, and meter inaccuracies. This system leverages modern technologies to provide a comprehensive solution for managing NRW.

## Features:-
1. Dashboard: Real-time monitoring of water distribution and consumption.
2. Leak Detection: Identify potential leaks using data analytics.
3. Reporting: Generate detailed reports on water losses.
4. User Management: Role-based access control for different types of users.
5. Alert System: Receive notifications for unusual water usage patterns.

## Technology Stack:-
Frontend: Angular
    Angular CLI
    RxJS
    Angular Material

Backend: Spring Boot
    Spring MVC
    Spring Data JPA
    Spring Security

Database: MySQL

Other Tools:
    Maven (for dependency management)
    Hibernate (ORM)
    RESTful APIs

## Architecture:-
The system follows a microservices architecture, divided into frontend and backend services. The frontend is developed using Angular, providing a responsive and interactive user interface. The backend, built with Spring Boot, handles the business logic and data processing, interfacing with a MySQL database for data storage.

## Setup and Installation:-

Prerequisites:-
    Node.js (v12 or higher)
    Angular CLI
    Java JDK (v11 or higher)
    Maven
    MySQL Server

Frontend (Angular):-

1. Clone the repository:
git clone https://github.com/yourusername/nrw-angular.git
cd nrw-angular

2. Install dependencies:
npm install

3. Run the development server:
ng serve

4. Open your browser and navigate to http://localhost:4200.

Backend (Spring Boot):-

1. Clone the repository:
git clone https://github.com/yourusername/nrw-springboot.git
cd nrw-springboot

2. Update the application.properties file with your MySQL database configuration:
spring.datasource.url=jdbc:mysql://localhost:3306/nrwdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

3. Build the project using Maven:
mvn clean install

4. Run the Spring Boot application:
mvn spring-boot:run

5. The backend server will start at http://localhost:1200

## Usage
1. Access the system via the frontend URL http://localhost:4200.
2. Register an account or login with existing credentials.
3. Use the dashboard to monitor water distribution and consumption.
4. Generate reports and analyze data to identify and manage non-revenue water.