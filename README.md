JetBrains Help Page – UI Test Automation Framework
A lightweight Selenium + TestNG automation framework designed to test the JetBrains Help Page UI.
The project demonstrates clean Page Object Model structure, reusable components, and automatic screenshot capturing on test failures.

Features
- Selenium WebDriver for browser automation
- TestNG for test structure, annotations, and listeners
- Page Object Model (POM) for clean separation of concerns
- Automatic screenshots on test failure (saved in /screenshots)
- Custom TestNG Listener for failure handling
- Reusable BaseTest and BasePage classes
- Cookie handling and navigation helpers
- Support for multiple test classes

How the Tests Work
BaseTest
- Initializes WebDriver
- Provides takeScreenshot() method
- Handles driver lifecycle
- Exposes driverReady flag to ensure screenshots only run when the browser is active
  BasePage
- Stores driver, wait, and JavaScript executor
- Provides shared utilities for all page objects
  Page Objects
  Each page class contains:
- Locators
- Actions
- Navigation helpers
  Test Classes
  Each test:
- Starts the browser
- Navigates to the JetBrains Help Page
- Accepts cookies
- Interacts with UI elements
- Verifies expected behavior

Automatic Screenshots
When a test fails, the listener triggers:
screenshots/
TestName_YYYYMMDD_HHMMSS.png

This helps diagnose flaky tests and UI issues.

Running the Tests
From the project root:
mvn clean test


Maven will:
- Download dependencies
- Launch TestNG
- Run all tests
- Save screenshots for failures

Technologies Used

Java 17+            Programming language

Selenium WebDriver  Browser automation

TestNG              Test framework

Maven               Dependency management

ChromeDriver        Browser driver

Apache Commons IO / Java NIOFile operations










