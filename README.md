# prime-numbers
# All the configurations (Thread pool params and chunk size etc) can be done in application.properties
# Default port of the application is set to 8777
# once spring boot application is started, please access it using eg: http://<hostname>:<port>/primes/10
# integration tests can be executed by running CucumberIntegrationTest.java file
# You can pass an optional parameter for selecting different algorithm as http://<hostname>:<port>/primes/10?algorithm=BF (can pass BF/CA for different algorithms)
# Two algorithms supported as of now (Brute force - BF and common algorithm - CA)
# Swagger URL http://<hostname>:8777/swagger-ui/index.html
