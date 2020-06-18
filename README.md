# Java, JUnit and Selenium Automation Test Framework

Basic automation test framework following action and page object models, using Selenium to interact with the web browser and JUnit as a test engine to run the tests in the framework.

## Prerequisites

* JDK should be installed
* Maven should be installed
* JUnit should be installed

## Configuration

In the map 'properties' is a 'config.properties' file. These are the valid configurations:

|     Key     |  Values | Description                                                                                           |
|:-----------:|:-------:|-------------------------------------------------------------------------------------------------------|
| browser     |  Chrome | The browser you want to use to run the tests with                                                     |
|             |   Edge  |                                                                                                       |
|             | Firefox |                                                                                                       |
| cloud       |   True  | Run the tests on any cloud testing platform using the 'username', 'accesskey' and 'gridurl' specified |
|             |  False  |                                                                                                       |
| Headless    |   True  | Run the test with or without GUI, currently not supported in Edge                                     |
|             |  False  |                                                                                                       |
| wait        |  1-...  | Default wait time (in seconds)                                                                        |
| username    |         | Username used by any cloud testing platform                                                           |
| accesskey   |         | Accesskey provided by any cloud testing platform                                                      |
| gridurl     |         | Gridurl provided by any cloud testing platform                                                        |
| chromepath  |         | Location of Chrome driver                                                                             |
| firefoxpath |         | Location of Firefox driver                                                                            |
| edgepath    |         | Location of Edge driver                                                                               |
| platform    |         | Name of OS (depends on cloud testing platform)                                                        |

* [Selenium](https://www.selenium.dev/) - The testing framework used
* [JUnit](https://junit.org/junit5/) - The testing framework used to do Unit tests
* [Maven](https://maven.apache.org/) - Dependency Management

## Running the tests

Open a terminal/command prompt and navigate to the root of the tests and run ```mvn test```
OR
Execute the ```run.bat``` file


## Useful links

* [JUnit5 User Guide](https://junit.org/junit5/docs/snapshot/user-guide/)
* [Selenium Documentation](https://www.selenium.dev/documentation/en/)

### Authors

* **Jordy Mous** - *Developer & Tester* - [Jordy Mous](https://www.linkedin.com/in/jordy-mous-3164b1130/)
* **Vincent Joossen** - *Developer & Tester* - [Vincent Joossen](https://www.linkedin.com/in/vincentjoossen/)
* **Zohal Shakeri** - *Developer & Tester* - [Zohal Shakeri](https://www.linkedin.com/in/zohalshakeri/)