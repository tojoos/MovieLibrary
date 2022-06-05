## Movie Library

*Simple REST API created using Spring Boot with mySQL database created in docker container. Data migration is performed using flyway. The connection is realised with jdbc template and mySQL driver.*

### How to set up application:
- Create environment variables: 
  - `MYSQL_DATABASE = "DB name"`
  - `MYSQL_LOGIN = "DB login"`
  - `MYSQL_PASSWORD = "DB password"`
  

- Have Docker installed, setup mySQL container: 
  - `$ docker pull mysql` 
  - `$ docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=${MYSQL_PASSWORD} -d -p 3307:3306 mysql`


- Build project with maven and run spring boot application:
  - `$ mvn clean install` 
  - `$ mvn spring-boot:run` 


- Done! Enjoy working application