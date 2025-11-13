# Test Automation Framework

This is an enterprise-grade test automation framework built using **Selenium WebDriver**, **TestNG**, and **Log4j2** in **Java**. It supports both **UI** and **API** automation, parallel execution and logging

---

## Table of Contents
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Running Tests](#running-tests)
- [Logging](#logging)

---

## Project Structure
```
TestAutomationFramework/
├─src/test/java
│      │     ├── com.aarushi.automation.ui
│      │     │   ├── pages/            # Page Object Models
│      │     │   ├── tests/            # Test classes
│      │     │   ├── utilities/        # Helper classes (e.g., ScreenshotUtils)
│      │     │   ├── model/            # Pojo classes
│      │     │   ├── base/             # Base classes,  WebDriverFactory
│      │     │   ├── listeners/        # Reporting
│      │     │   └── actions/          # Action wrapper methods,
│      │     │
│      │     │
│      │     └── com.aarushi.automation.api/
│      │         ├── Services/         # API Service definitions (Service Object Model)
│      │         ├── Tests/            # API test classes
│      │         ├── Config/           # AppSettings.json for API tests
│      │         ├── Base/             # Base service, RestClient factory
│      │         ├── Data/             # API request and schema files
│      │         ├── Models/           # API request and response DTOs
│      │         └── Utilities/        # ConfigReader and JsonSchemaValidator
│      │            # Logging configuration
│      │ 
│      └──resources
│              ├──ui
│              │   ├── configuration
│              │   └── testdata
│              ├── api
│              │   ├── configuration
│              │   └── testdata
│              │
│              └──log4j2.xml
│                  
├── pom.xml
├── testng.xml
├── README.md
└── .github
        └── workflows
                └── test.yml


```

## Technologies Used
- **Java**
- **Selenium WebDriver** for UI automation
- **TestNG** for test execution and test categorizations
- **Log4J2** for logging
- **RestAssured** for API automation
- **Jackson** for JSON handling


## Features
- Page Object Model (POM) design pattern for UI tests which significantly enhances the scalability of test automation frameworks
- Service Object Model (SOM) design pattern for API tests to support enterprise level microservices architecture
- Base API service for building HTTP requests
- Parallel test execution
- Thread-safe actions during parallel execution
- Handling of browser specific drivers using WebDriverManger
- Logging to **console** and **log files** using log4j2
- JSON schema validation for API schema validation
- API request and response DTO's to handle complex json responses by deserializing the API response to POJO objects
- UI and API components are in separate package which makes the framework scalable and we can add another package in future for mobile testing using appium and keep shared code in common package.
- Running test in CI server using GitHubActions workflow


## Prerequisites
- [Java 20](https://www.oracle.com/au/java/technologies/downloads/)
- [Maven 3.9.11](https://maven.apache.org/download.cgi)
- Internet access for downloading dependencies
- Chrome and Edge browsers are installed in your machine
- No need to installed browser specific drivers as it is automatically handled through WebDriverManager


## Running Tests on mac using zsh terminal
Follow these steps to execute the tests on your Mac machine:
1. Run all tests (both UI and API) with console logging. By default the UI test will run on chrome browser
```bash
mvn clean test
```
2. Run only UI test with package name
```bash
mvn clean test -Dtest="com.aarushi.automation.ui.**"
```
3. Run only UI test in edge browser
```bash
mvn clean test -Dtest="com.aarushi.automation.ui.**" -Dbrowser=edge
```
4. Run only UI test in chrome browser
```bash
mvn clean test -Dtest="com.aarushi.automation.ui.**" -Dbrowser=chrome
```
5. Run only UI test of specific groups
```bash
mvn clean test -Dtest="com.aarushi.automation.ui.**" -Dgroups=regression
```
6. Run only UI test in parallel.
```bash
mvn clean test -Dtest="com.aarushi.automation.ui.**" -Dparallel=methods -DthreadCount=3
```
7. Run only UI test in headless mode for CI run
```bash
mvn clean test -Dtest="com.aarushi.automation.ui.**" -Dheadless=true

```

## Logging
- Logging is handled by Log4j2.
- Logs are written to: /target/logs/framework.log
- Thread ID is logged for parallel execution.


## Further Enhancements
- Appium integration