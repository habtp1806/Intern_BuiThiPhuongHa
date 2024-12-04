# Selenium Railway System Test

This project contains automated tests for the Railway System using Selenium WebDriver with Java. Website for book train
ticket.

## Table of Contents

- [Project Overview](#project-overview)
- [Setup Instructions](#setup-instructions)
- [Running Tests](#running-tests)
- [Setting up Selenium Grid](#setting-up-selenium-grid)
- [Setting up Selenium Grid with Docker](#setting-up-selenium-grid-with-docker)
- [Setting up Selenium Grid with Json](#setting-up-selenium-grid-with-json)
- [Run tesng.xml parallel browsers](#run-tesngxml-parallel-browsers)

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
      java -jar selenium-server-4.22.0.jar hub
      ```
    - The Hub will start and listen for incoming WebDriver requests on port 4444 by default.


3. **Start Selenium Node**:
    - Open another terminal or command prompt.
    - Start a Node to connect to the Hub. Replace `<HUB_IP>` with the IP address or hostname of the machine running the
      Hub:
      ```sh
      java -jar selenium-server-4.22.0.jar node --hub http://<HUB_IP>:4444
      ```
    - The Node will register itself with the Hub and become available for executing browser sessions.


4. **Verify Grid Setup**:
    - Open a web browser and navigate to the Grid console to verify the setup:
      ```
      http://<HUB_IP>:4444/grid/console
      ```
    - The console will display information about the Hub, connected Nodes, and available browser configurations.


5. **Run with terminal**
    ```sh
    mvn clean test -Drunmode=grid
      ```

### Running Tests with Selenium Grid

To run your tests using Selenium Grid, configure your test automation framework (e.g., TestNG, JUnit) to use the Hub
URL (`http://<HUB_IP>:4444`) for RemoteWebDriver initialization. Specify the desired browser and platform capabilities
in your test configuration.

### Setting up Selenium Grid with Docker

1. **Install Docker**: Ensure Docker is installed on your machine.


2. **Start Docker Services**:
    - Open another terminal or command prompt.
    - Navigate to the directory containing the docker-compose.yml file
    - Run the following command to start the Selenium Grid services:
     ```sh
      docker-compose up -d
      ```
    - This will start the Selenium Hub and Node services defined in the docker-compose.yml file.


3. **Verify Grid Setup**:
   ```sh
     http://localhost:4444/grid/console
   ```
    - The console will display information about the Hub, connected Nodes, and available browser configurations.

### Setting up Selenium Grid with Json

1. **Start Selenium Hub**: Start the Selenium Hub on your local machine or server. Open a terminal or command prompt,
   navigate to the directory where you have the Selenium Server jar file, and run the following command:
   ```sh
     java -jar selenium-server-4.22.0.jar hub
   ```
2. **Start Selenium Node with Custom Configuration**: Start a Selenium Node using the configuration specified in the
   nodeConfig.json file. Open another terminal or command prompt, navigate to the directory where you have the Selenium
   Server jar file, and run the following command:

   ```sh
    java -jar selenium-server-4.22.0.jar node --config nodeConfig.json
   ```
3. **Verify Grid Setup**: To verify that your Selenium Grid is set up correctly, open a web browser and navigate to the
   Grid console at:

   ```sh
    http://localhost:4444/grid/console
   ```

### Run tesng.xml parallel browsers

```sh
mvn clean test -Drunmode=local
```
