## Matts Food Hygiene Application

Project contains a React frontend and a Java SpringBoot backend service. 

### Assumptions
1. Percentage values should be displayed as integers and add up to 100%
2. The client should be flexible and accept any possible rating representation from the external API.
3. If the percentage of a rating is zero it should still be displayed.

### Core features

#### Backend
1. SpringBoot app
2. RESTful(ish) WebService
3. Consuming third-party API using RestTemplate.
4. Implemented algorithm rounds percentage floating point numbers to integers, so that they sum up to one hundred. It is designed to minimalize relative approximation error. It performs at O(N) time complexity. (where N is a number of different percentage values). For more information see `LeastErrorPercentageCalculatorTest`
5. Fully tested with JUnit, Mockito and Spring utils. 


#### Frontend
1. React 16 app
2. Redux for managing application state
    1. thunk-middleware to handle side effects (like displaying failure notification) 
    2. promise-middleware to handle async actions and create reasonable UX
3. Simple bootstrap styling
4. eslint for identifying and reporting on patterns to ensure code quality

### Running the application

1. Make sure you have installed the following:
    1. java 8+
    2. maven
    3. npm
    
2. Navigate to root of the project

3. Configure properties (or leave the defaults):

    `mattsfoodhygiene/backend/src/main/resources/appliction.properties`
    
    should contain:
    ```
    food.hygiene.api.baseUrl=http://api.ratings.food.gov.uk:80
    food.hygiene.frontend.url=http://localhost:<FRONTEND_PORT>
    server.port=<BACKEND_PORT>
    ```

    `mattsfoodhygiene/frontend/src/config/env.json`
    ```json
    {
      "development": {
        "BASE_URL": "http://localhost:<BACKEND_PORT>"
      },
      "production": {
        "BASE_URL": "http://localhost:<BACKEND_PORT>"
      }
    }
    ```
    
4. Build the project:
    `mvn clean verify`
    
5. Run the backend service:
    
    `java -jar backend/target/backend-0.0.1-SNAPSHOT.jar`
    
6. Navigate to frontend directory
   
    `cd frontend`
    
7. Get all the dependencies
    
    `npm install`
    
8. Run the frontend application

    `npm run start`
    
    or if you want to run on a specific port:
    
    `PORT=<FRONTEND_PORT> npm run start`
    
9. Open browser and navigate to the URL (port 3000 by default):

    `http://localhost:<FORNTEND_PORT>`

