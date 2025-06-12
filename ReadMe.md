This maven project contains two submodules: common/core module of test automation framework and acceptance test module

Technologies used: Spring, CucumberJVM, TestNG, Selenide, RestAssured, Allure 

### Table of contents
1. [Required Software and Tools & Prerequisites](#required-software-and-tools)
2. [How to build maven projects and run all tests](#how-to-run-acceptance-tests)
3. [Reports](#reports)

<a name="required-software-and-tools"></a>
### Required Software and Tools & Prerequisites

* **Java** version: **JDK 21** and higher (Execute `java --version` in command line after installation)
* **Apache Maven** version: **3.9.10** and higher (Execute `mvn --version` in command line after installation)
* **Allure commandline** - instructions on [how to install](https://www.npmjs.com/package/allure-commandline)

 <a name="how-to-run-acceptance-tests"></a>
### How to build maven projects and run all tests 

* Open a terminal or command prompt
* Go to project's root
* Execute `mvn clean install` in order to run tests
* Execute `mvn clean install -Dcucumber.filter.tags="@api"` in order to run only API tests
* Execute `mvn clean install -Dcucumber.filter.tags="@ui"` in order to run only UI tests in headless mode (could fail due to of Google Captcha)
* Supported browsers are: Chrome, ChromeHeadless(default). Pass following parameter `-Dbrowser=Chrome` in order to run UI tests in headed mode.
* In order to configure number of threads when running tests in parallel mode pass `-Ddataproviderthreadcount=4` argument with custom value

<a name="reports"></a>
### Reports  
* Run `mvn -f acceptance-tests/pom.xml allure:serve` in order to generate and open Allure report in browser
* **Allure** html test report should be available under `acceptance-tests/target/site`
