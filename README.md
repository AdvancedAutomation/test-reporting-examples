# report-samples 
# Environment setup
Create an account on https://www.pivotaltracker.com/ and set the following variables on gradle.properties.
- set baseUrl = https://www.pivotaltracker.com/services/v5
- set token = (your token)
- set schemasPath = src/test/resources/schemas/
- set filterTags = 
- set cucumberThreadCount = (the number of threads)

# Test reporting example branches
- cucumberReport (Adds support for native Cucumber Reporting tool)
- allureReport (Adds support for Allure Reporting tool, additional config files and custom Cucumber plugin support)
- extentReport (Adds support for Extent Reporting tool)
- reportPortal (Adds support for Report Portal tool)
- customFormatter (Basic implementation for custom Cucumber formatter plugin)

# Gradle tasks
- ./gradlew clean check
- ./gradlew clean executeBDDTests
- ./gradlew reExecuteBDDTests
