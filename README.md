# API Automation Testing: Part 4: 

This project automates the testing of APIs for a local server using RestAssured, Java, and TestNG.
 
Setup
1-Clone the repository:   git clone https://github.com/bestbuy/api-playground.git

2-Navigate to the project directory:  cd api-playground

3-Install dependencies using Maven: mvn clean install
4-npm install
5-npm start
# Best Buy API Playground started at http://localhost:3030


Deliverables

1-A list of test cases proposed for automation:
        -Health check
        -Get all products
        -Get product by ID
        -Get products by category
        -Search products
        -Validate product schema
        -Add a product
        -Delete a product
        -Get all stores
        -Get store by ID
        -Get store services
        -Get store location
        -Search stores
        -Get all services
        -Get service by ID
        -Search services
        -Validate created and updated timestamps
2-Automate the proposed list of test cases:
        -All the above test cases are automated in the respective test classes: HealthCheckTest, ProductsTest, StoresTest, and ServicesTest.

3-Short explanation of the provided solution:
        1-The project uses RestAssured for API testing, Java as the programming language, and TestNG as the test framework.
        2-TestBase class initializes the base URL from config.properties.
        3-Each test class contains methods to test different endpoints and validate responses.
        4-The tests use assertions to ensure the correctness of the API responses.

4-You can run it via Testng.xml





# Automation UI: Part 3 :
This project automates the registration and login scenarios for the Petfinder website using Selenium, Java, and TestNG with a data-driven approach.

1-The solution is built using Selenium WebDriver for browser automation, Java as the programming language, and TestNG as the test framework.
2-The TestBase class initializes the WebDriver and loads the configuration properties.
3-The test classes LoginTest and RegistrationTest use the data-driven approach to test multiple sets of data for registration and login scenarios.
4-The TestData class provides a centralized location for generating random test data.



