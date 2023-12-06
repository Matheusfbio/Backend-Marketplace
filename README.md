## Marketplace

This is a Spring Boot project for the purpose of creating a spring boot backend of Marketplace for studies.

## Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) version 17.
- Maven for building and managing the project.
- Git for clone this project.

Getting Started
To get started with this project, follow these steps:

Clone the repository to your local machine:

```
git clone https://github.com/Matheusfbio/Backend-Marketplace.git
```

Change your working directory to the project folder:

```
cd bk-spring-boot
```

Build the project using Maven:

```
mvn clean install
```

Start the application:

```
java -jar target/bk-spring-boot-<version>.jar
```

Replace <version> with the actual version of the JAR file generated.

Usage
Describe how to use the project, including any specific configurations, environment variables, or other requirements.

Swagger API Documentation
This project uses Swagger for API documentation. You can access the API documentation by following these steps:

Start the application as mentioned in the Getting Started section.

Open your web browser and go to the following URL:

```
http://localhost:8080/swagger-ui.html
```

This will open the Swagger UI, where you can explore the available endpoints, make API requests, and view documentation.

If you want to access the raw Swagger JSON file, you can use the following URL:

```
http://localhost:8080/v2/api-docs
```

This JSON file can be used with Swagger UI or other tools for API documentation and testing.
