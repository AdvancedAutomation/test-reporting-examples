# report-samples 
# Environment setup
Create an account on https://www.pivotaltracker.com/ and set the following variables on gradle.properties.
- set baseUrl = https://www.pivotaltracker.com/services/v5
- set token = (your token)
- set schemasPath = src/test/resources/schemas/
- set filterTags = 
- set cucumberThreadCount = (the number of threads)

# Gradle tasks
- ./gradlew clean check
- ./gradlew clean executeBDDTests
- ./gradlew reExecuteBDDTests
