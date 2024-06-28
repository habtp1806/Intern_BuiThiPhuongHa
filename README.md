# Selenium Railway System Test

This project contains automated tests for the Railway System using Selenium WebDriver with Java. Website for book train
ticket.

## Table of Contents

- [Project Overview](#project-overview)
- [Setup Instructions](#setup-instructions)
- [Running Tests](#running-tests)

## Project Overview

The Selenium Railway System Test project aims to automate the testing of a railway booking system. The automated tests
include:

- Logging in with valid credentials.
- Navigating to the Timetable page.
- Checking prices for specific routes, such as "Sài Gòn - Đà Nẵng".

## Setup Instructions

### Prerequisites

Ensure you have the following installed on your machine:

- Java Development Kit (JDK) 11 or higher
- Apache Maven
- Google Chrome browser
- ChromeDriver
- Selenium Server (for Selenium Grid setup)

### Running Tests

To run the tests, you can use Maven. The browser type can be specified via the command line using the `-Dbrowser`
parameter. By default, the tests will run in Chrome.

#### Running Tests Different Browsers

```sh
#Run a single test class.
mvn clean test -Dbrowser=firefox -Dtest=RegisterTest
mvn clean test -Dbrowser=firefox -Dtest=RegisterTest
mvn clean test -Dbrowser=edge -Dtest=RegisterTest
# Run all the unit test classes
mvn clean test -Dbrowser=firefox
# Run multiple test classes.
mvn clean test -Dbrowser=firefox -Dtest=RegisterTest,LoginTest
```

### Setting up Selenium Grid

Selenium Grid allows you to distribute tests on multiple machines, making it ideal for parallel testing across different
browsers and platforms. Follow these steps to set up Selenium Grid:

1. **Download Selenium Server**:
    - Download the Selenium Server standalone jar file from the Selenium website or Maven Central repository.

2. **Start Selenium Hub**:
    - Open a terminal or command prompt.
    - Navigate to the directory where you downloaded the Selenium Server jar file.
    - Start the Hub by running the following command:
      ```sh
      java -jar selenium-server-4.21.0.jar hub
      ```
    - The Hub will start and listen for incoming WebDriver requests on port 4444 by default.

3. **Start Selenium Node**:
    - Open another terminal or command prompt.
    - Start a Node to connect to the Hub. Replace `<HUB_IP>` with the IP address or hostname of the machine running the
      Hub:
      ```sh
      java -jar selenium-server-4.21.0.jar node --hub http://<HUB_IP>:4444
      ```
    - The Node will register itself with the Hub and become available for executing browser sessions.

4. **Verify Grid Setup**:
    - Open a web browser and navigate to the Grid console to verify the setup:
      ```
      http://<HUB_IP>:4444/grid/console
      ```
    - The console will display information about the Hub, connected Nodes, and available browser configurations.

### Running Tests with Selenium Grid

To run your tests using Selenium Grid, configure your test automation framework (e.g., TestNG, JUnit) to use the Hub
URL (`http://<HUB_IP>:4444`) for RemoteWebDriver initialization. Specify the desired browser and platform capabilities
in your test configuration.
