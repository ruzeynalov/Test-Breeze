This maven module contains e2e acceptance tests

### Table of contents
1. [Required Software and Tools & Prerequisites](#required-software-and-tools)
2. [How to build maven projects and run all tests](#how-to-run-acceptance-tests)
3. [Reports](#reports)

<a name="required-software-and-tools"></a>
### Required Software and Tools & Prerequisites

* **Java** version: **Oracle Java 17** and higher (Execute `java -version` in command line after installation)
* **Apache Maven** version: **3.9.9** and higher (Execute `mvn -version` in command line after installation)

 <a name="how-to-run-acceptance-tests"></a>
### How to build maven projects and run all tests 

* Open a terminal or command prompt
* Go to project's root
* Execute `mvn clean install` for all test suites
* Execute `mvn clean install -Dcucumber.filter.tags="@api"` in order to run only API tests
* Execute `mvn clean install -Dcucumber.filter.tags="@ui"` in order to run only UI tests
* Supported browsers are: Chrome, ChromeHeadless(default). Pass following parameter `-D browser=Chrome` in order to run UI tests headed mode.
* In order to configure number of threads when running tests in parallel mode pass `-Ddataproviderthreadcount=4` argument with custom value


<a name="reports"></a>
### Reports
* Run `mvn allure:serve` in order to generate and open Allure report in browser
* **Allure** html test report should be available under `acceptance-tests/target/site`