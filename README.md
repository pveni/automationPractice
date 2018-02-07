# automationPractice
Automation Practice website testing

Project Name: Automation Practice

Project Description: Tests have been automated for three scenarios on the website to check the functionality and as well for rest services
api testing

Framework:
1. Maven project with Selenium Webdriver using Java Language for functional tests -> path //src/test/java/tests
2. Rest Assured using Java Language for API tests -> path //src/test/java/apitests

Framework is designed with Page Object Model where you find all the pages with its elements here //src/main/java/Pages

Webdirver: This project has used Firefox browser for testing. We can also include multiple browsers if needed. For the configuration information
you can find the path here //src/main/java/Configuration/Page

TestRun: Used TestNG for running tests. You can find //testng.xml where all the tests are included in one suite.

Test Report: Once we run the test suite or individual test you can see the reports here //test-output/Automation Practice Test Suite with html format
If test case fails the image is captured here //test-output/failedScreenshots.





