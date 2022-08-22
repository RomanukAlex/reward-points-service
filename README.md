# Calculate the reward points
* [General info](#general-info)
* [Technologies](#technologies)
* [Installing](#installing)
* [Running test](#running-test)
* [Custom Exception](#custom-exception)
* [Health check](#health-check)
* [Logger](#logger)
* [Test data](#test-data)
* [Expected result](#expected-result)
* [Docker](#docker)

## General info
Test task

## Technologies
- JDK11
- Spring Boot 2
- Maven 3
- Database - H2 (in memory)

## Installing
To clone and run this application, you'll need:
- Download project from github URL:

      git clone https://github.com/RomanukAlex/reward-points-service.git

- Go into the repository:

      cd reward-points-service

- Build project

      mvn clean package

- Run applications with H2 database (in memory): 

      mvn spring-boot:run

- Open 

      http://www.localhost:8099/v1/customers/reward-points-info?language=EN
      Languages: EN, ES, FR

## Running test
    mvn test

## Custom Exception
    LanguageNotFoundException. Message - No language found. Valid languages:[EN, ES, FR]
    TransactionNotFoundException. Message - No transactions found.

Also use an ExceptionHandler for a generic exception to have the same style error message.

## Health check
    http://www.localhost:8099/actuator/health

## Logger
Add info logger to check language and error logger to check errors in exception groups.
- Check logger level:

      http://www.localhost:8099/actuator/loggers

- Root level:

      http://www.localhost:8099/actuator/loggers/ROOT

- Change logger level. Example:

      POST http://www.localhost:8099/actuator/loggers/ROOT
      Body: {"configuredLevel": "TRACE"}

## Test data
![test-data.png](image/test_data.png)

## Expected reward points result
Name | December | January | February | March | April| Total |
:---: | :---: | :---: | :---: | :---: | :---: | :---: |
Alex | 156 | 0 | 2 |   |   | 158 |
Mike |   |   | 0 | 1 | 52 | 53 |
Nick |   |   | 90 | 90 | 90 | 270 |

## Docker
- Download project from github URL:

      git clone https://github.com/RomanukAlex/reward-points-service.git

- Go into the repository:

      cd reward-points-service

- Build project

      mvn clean package

- Build docker image

      docker build -t test-task/calculate-reward-points .

- Run docker container

      docker run -p 8099:8099 test/reward-points-docker
