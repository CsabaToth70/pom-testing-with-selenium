# pom-testing-with-selenium

## About the project

This project was implemented in 3rd-week pair of advanced Test Automation training modules.
Our task was to test the following features of the Jira webpage:

1. Login
2. Logout
3. Browse Project
4. Create Issue
5. Browse Issue
6. Edit Issue

In the first phase of the work, we created test cases design in Step-by-Step form, ran them manually and with Selenium IDE.
We worked in pair programming, divided the Jira features among each other.
Based on the test suite runs, some fine-tune aimed modification was reasoned on test cases before we considered them finalized.

We aimed to write and run POM based automation test for the Jira functions mentioned above.


## Applied technologies

Project SDK: java 11 version 11.09
Project language level: 16 Records, patterns
Applied browser: Google Chrome Version:  91.0.4472.106 (64 bit)
Applied WebDriver: chromedriver
Framework: Selenium with Page Object Model
Data resources format: .csv
Applied IDE: IntelliJ IDEA


## Technical information and set up

For Jira page testing, the user need to have to registered username and password for Codecool meta stage of Jira:
https://jira-auto.codecool.metastage.net/

Username and password for Jira: should be placed 'app.properties' named file property file saved into main project 
directory in this format:

        username=<username>
        password=<password>

Chromedriver have to be saved as 'chromedriver' named file also in the main project folder, 
selected for proper version of the browser: https://sites.google.com/a/chromium.org/chromedriver/downloads


## User information

When you run these test suites, the browser has to open and close each test case, to ensure independent test results from each other.
The test cases contain click and type input data into fields of the webpage automatically, and some wait methods for the concerned fields to be clickable.
The lengths of waits were designed in our technical environment (network speed, browser-related WebDriver).

It could happen that sets of these "waits" should be increased by a further 3-5 seconds, due to different environments.


## Story

1. Creating & Running Automated Testcases.
2. Improve and Stabilize Automated Tests with Page Object Model (POM) design pattern.