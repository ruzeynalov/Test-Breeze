This maven module contains e2e acceptance tests

### Table of contents
1. [Required Software and Tools & Prerequisites](#required-software-and-tools)
2. [How to build maven projects and run all tests](#how-to-run-acceptance-tests)
3. [Reports](#reports)

<a name="required-software-and-tools"></a>
### Required Software and Tools & Prerequisites

* **Java** version: **Oracle Java 8** and higher (Execute `java -version` in command line after installation)
* **Apache Maven** version: **3.6.1** and higher (Execute `mvn -version` in command line after installation)

 <a name="how-to-run-acceptance-tests"></a>
### How to build maven projects and run all tests 

* Open a terminal or command prompt
* Go to project's root
* Execute `mvn clean install -D cucumber.options="--tags @api"` in order to run API tests
* Execute `mvn clean install -D cucumber.options="--tags @ui"` in order to run UI tests
* Execute `mvn clean install` for all test suites
* Supported browsers are: Chrome(default), ChromeHeadless, Selenoid (dockerized Chrome). Pass following parameter `-D browser ChromeHeadless` in oder to run UI tests in headless mode

<a name="reports"></a>
### Reports  

* **Allure** html test report should be available under `acceptance-tests/target/site/allure-maven-plugin` after test execution
