ava Web Application with REST API

!! THERE ARE NO TESTS AND LOGGING

Overview

This Java-based web application provides a RESTful API with a focus on security and performance. It features user authentication and authorization using JWT tokens, access control based on user roles, and a relational database for persistent data storage. The application is designed with a clean architecture, separating concerns into repository, service, and controller layers, alongside utility classes for common functions.

Features

User authentication and authorization with JWT (JSON Web Tokens)
Weather data fetching and processing from OpenWeatherMap
CRUD operations for city data management
Secure REST API endpoints
Incorporates logging for monitoring and debugging purposes.
Project Structure:
repository: Data access layer for interacting with the database.
service: Contains business logic and data processing.
util: Utility classes and common functions.
controller: REST controllers to handle API requests.
API Endpoints

...

Parameters and Body Details

need to use Swagger for this

Getting Started

Prerequisites

JDK 17 or later
Maven
A relational database (e.g., MySQL, PostgreSQL)
Setup

Clone the repository to your local machine.
Configure the database connection in src/main/resources/application.properties.
Run the database migration scripts located in src/main/resources/db/migration to set up your database schema.
Build the project using Maven: mvn clean install.
Run the application: java -jar target/your-application.jar. (+ openweather map api key and security related things (token secret and duration))
Usage

After starting the application, you can interact with the API using tools like Postman or Curl. Below are some example requests:

Authenticate
