Spring Boot Demonstration Project


STRUCTURE:
The examples are divided under two categories. The ones under fsl directory are directly related to the fsl project.
The other category intern is for internal use only. As such it includes general purpose Spring Framework examples.

In each category there is a single Spring Boot project under boot directory. All Spring Boot related examples can be found in this project.
Spring Cloud examples have been spread to seperate projects each named after the general aspect that is demonstrated. The cloud projects have
been structured in this way to prevent dependency conflicts.


RUNNING THE EXAMPLES:
Please use spring-boot plugin run goal to run the projects(from command line=>mvn spring-boot:run). In some cases where some preprocessing is required(e.g. RestDocs examples where
asciidoc plugin compiles asciidoc files) run install(mvn install) before the spring-boot plugin run goal. 

TESTING:
run mvn test command