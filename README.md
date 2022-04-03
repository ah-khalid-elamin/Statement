# Java Assignment

This is a backend project for retrieving bank accounts from a sample Microsoft Access database
using Spring Boot and OAuth2 for authentication and authorization using Role Based Access Control.


## Features
The server will handle requests to view statements by performing simple search on date and 
amount ranges.
- The request should specify the account id.
- The request can specify from date and to date (the date range).
- The request can specify from amount and to amount (the amount range).
- If the request does not specify any parameter, then the search will return three months 
back statement.
- If the parameters are invalid a proper error message is sent to user.
- The account number is hashed before sent to the user.
- All the exceptions are handled on the server properly.

## Useful links
- [UCanAccess] - Hibernate Dialect for Microsoft Access Database.
- [OAuth]  - Industry-standard protocol for authorization.


[UCanAccess]: <https://github.com/MrErgos/UCanAccess-Hibernate-dialect/tree/main/src/main/java/net/ucanaccess/hibernate/dialect>

[OAuth]: <https://oauth.net/2/>
