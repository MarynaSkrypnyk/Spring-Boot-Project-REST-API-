# Spring Boot(Rest-API)



### Overview

This Java-based web application provides a RESTful API with a focus on security and performance. This provides user authentication and authorization using JWT tokens, user verification using activation codes, access control based on user roles, and relational databases for persistent data storage. The application is designed with a simple architecture that separates tasks into repository, service and controller levels, configuration files and utilities.


### Features

- user authentication and authorization using JWT (JSON web tokens);
- user verification using an activation code, which the user receives by email upon registration;
- receiving data from open api;
- CRUD operations for user data, menus, orders;
- secure REST API endpoints;
- data caching;
- strict data validation;

Project structure:
- repository: data access layer for interacting with the database.
- service: Contains business logic and data processing.
- util: utility classes and general functions.
- controller: REST controllers for processing API requests.
- API endpoints
- configuration: configuration files:
- entities: definition of entities:
- security: responsible for authorization and auto-identification;
- model: defining the data that comes from the endpoint
- resources: dbchangelog - master.xml for word with db

## Prerequisites

- JDK 17 or later
- Maven
- a relational database (e.g., MySQL, PostgreSQL)
- Setup

Clone the repository to your local machine.
Configure the database connection in src/main/resources/application.properties.
Run the database migration scripts located in src/main/resources/db/migration to set up your database schema.
Build the project using Maven: mvn clean install.
Run the application: java -jar target/your-application.jar. (+ openweather map api key and security related things (token secret and duration))
Usage

After starting the application, you can interact with the API using tools like Postman or Curl


## ## Parameters and Body Details

![Screenshot of a comment on a GitHub issue showing an image, added in the Markdown, of an Octocat smiling and raising a tentacle.](https://myoctocat.com/assets/images/base-octocat.svg)

