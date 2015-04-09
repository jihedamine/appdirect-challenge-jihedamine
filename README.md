# NotesApp

NotesApp is a note taking application that uses OAuth and OpenId for authentication and single sign on.
The application can be bought from an AppDirect Marketplace.

The application was made using:

- Spring Boot version 1.2.3 as a building framework 
- Spring Security to manage access to the application and setup OAUTH, OpenId and SSL
- Spring Web to implement MVC mapping and REST controllers
- Hibernate for Object-Relation Mapping (using standard JPA annotations)
- Thymeleaf as a templating engine for the User Interface

## Integration with AppDirect Marketplace

The application is integrated with AppDirect Marketplace.

- Purchase a subscription
- Change a subscription
- Cancel a subscription
- Handle subscription status change
- Assign a user
- Unassign a user
- Link billing, user management and logout to the marketplace where the application was purchased
- Use of custom user attributes (isAppAdmin) to determine if a user is an administrator inside the application.

## Showcase instance 
A showcase instance of NotesApp is deployed at https://appdirect-challenge-jihedamine.herokuapp.com
Email me at jihedamine-at-gmail-dot-com with your email so that I can invite you and assign you as a user of the application.
The showcase instance uses a PostgreSQL database provisioned by Heroku.

## Configuration

### Database configuration
You have to setup the following environment variables on the server where you will deploy the webapp
>>export NOTESAPP_DB_USERNAME=<your database username>

>>export NOTESAPP_DB_PASSWORD=<your database user password>

>>export NOTESAPP_DB_URL=<jdbc formatted url to the database>

>>export NOTESAPP_DB_DIALECT=<your database hibernate dialect>

For example, to connect the user 'myusername' who has the password 'secret' to the 'mydb' PostgreSQL database on localhost, we would have the following values:
>>export NOTESAPP_DB_USERNAME=myusername

>>export NOTESAPP_DB_PASSWORD=secret

>>export NOTESAPP_DB_URL=jdbc:postgresql://localhost:5432/mydb

>>export NOTESAPP_DB_DIALECT=org.hibernate.dialect.PostgreSQLDialect

### OAuth
You have to setup the following OAUTH environment variables on the server where you will deploy the webapp
>>export appdirect.consumer.key = <your oauth consumer key>

>>export appdirect.consumer.secret = <your oauth consumer secret>

These values can be found in your AppDirect Developer Portal, at "Edit Application" > "Edit Integration" > "OAuth Configuration"

## Running locally
The following command will launch an instance of the webapp on your localhost at port 8080 by default
>> mvn spring-boot:run

## Generating a WAR file
Issue the following command to create a WAR file of the webapp (and deploy it on your favorite web container)
>> mvn clean package

