# Sample Microservice developed in Spring Boot Netflix architecture.

The setup and running is explained below 👇.

# The setup consists of 4 modules:
1. Spring Eureka Server - name:eureka, port:9090
2. Spring Cloud Config - name:cloudconfig, port:8081
3. Spring Git Repos Stats Microservice - name:gitRepoTrendsService, port:any
4. Spring Zuul Gateway - name:gateway, port:8083
5. UI - Html file that calls the Microservice and graphically shows the data

First, we have to start Eureka server which listens for connections. We can up the server by running the Spring Boot Application. Eureka server takes care of ensuring the services registeered are up and running by regularly checking if the instances are reachable.

Second, we start the Cloud Config Server which acts as a client by connecting to Eureka server. Config Server provides support for externalized configuration in distributed environment. This will helps us while setting up CI-CD via Git etc. In the current project, native profile is selected so, any config files can be read from local file system itself.

Third, we can start our REST Microservice which will connect to Eureka Server and thereupon retrieve Config Server details by querying Eureka for Config Server and establishes connection to it. We can run any number of service instances for High Availability, simply by running as many Spring Boot runs as required. Since port is set to 0, any random port is considered while upping the instance.

Now, In a similar way we can start the Zuul Application Gateway which also connects to Eureka and queries for the services configured for routing in its application.properties file. Gateway acts as our front end listener and is essential that this be the entry point for our architecture as it supports various extension mechanisms like Application Security, Logging, Load Balancing, Routing etc. This is the application that receives requests from users when then open the UI.

Finally, once our back end environment is up and running, we can run the ui.html file in the browser. When the page loads, it calls our Application endpoint ```localhost:8083``` (note: this is the ip address of our gateway application)


## Problem

- Develop a REST microservice that list the languages used by the 100 trending public repos on GitHub.
- For every language, you need to calculate the attributes below 👇:
    - Number of repos using this language
    - The list of repos using the language

## Get Trending Repos from Github

Fetching trending repositories simply translates to fetching the most starred repos created in the last 30 days ( from now ). The following endpoint is called for fetching the required repositories information:

```
https://api.github.com/search/repositories?q=created:>{date}&sort=stars&order=desc&per_page=100
```

The paginated JSON data is retrieved and required fields from the response is extracted. For our problem, we need the following fields: ```stargazers_count```, ```language```, ```full_name```


## Process to calculate the git repo stats
Once, the required data is extracted, the microservice returns the extracted data as JSON string to the UI. In the UI, we perform transforms as required to restructure the data so that we can perform various operations to graphically represent the data in such a way that it outputs information as per our problem statement. For developing this UI, Google Charts api is used.

As seen from the attached images, it can be seen that the application is able to provide MicroService Instance info along with Git Repo Stats.
