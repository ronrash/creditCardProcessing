# credit-card-processing-service
maven project containing rest endpoints for serving credit card processing.

# To Build
> mvn clean package

# To run built package
> java - jar target/creditcardProcessing-0.0.1-SNAPSHOT.jar

# To hit build info 
> curl http://localhost:8081/actuator

# The two Endpoints exposed are
Post - http://localhost:8081/api/v1/cards

Response
Status Code 201  and Id of the record

Get - http://localhost:8081/api/v1/cards
[
{
"cardId": 1,
"name": "James",
"cardNumber": 4242424242426742,
"cardLimit": 0
},
{
"cardId": 5,
"name": "John",
"cardNumber": 4242424242426742,
"cardLimit": 0
}
]

To view the data :

Access DB : http://localhost:8081/h2-console/

Once you connect H2 database using console, you can see the tables created

See the record in DB :- SELECT * FROM CREDIT_CARD ;