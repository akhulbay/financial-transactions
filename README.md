# financial-transactions
This REST API is designed for conducting financial transactions and integrating with a simulated external payment system.

### Note:
For authentication use username: shyngys and password: 123

# Database Schema:
_______
![Диаграмма без названия drawio](https://github.com/akhulbay/financial-transactions/assets/117244670/55687be5-05fb-44b8-b6b1-b262542d6a38)

## Description:

**Account**: 
The Account entity contains personal user information. 

**Wallet**:
Since an account can have multiple wallets, it was decided to create the Wallet entity, which holds information about the balance, creation date, and has a reference to the Account (i.e., its owner). This enables transfers not only between wallets of different users but also between one's own wallets. 

**Transaction**:
To facilitate transfers, the Transaction entity was created. It is used to document each transfer in a granular manner. It includes fields such as amount, date and time of the transfer, as well as references to the sender's and recipient's wallets.

**User**:
The User entity, in turn, is needed to implement authentication and authorization.

# Technologies:
_______
+ Spring Boot
+ Spring Secirity
+ Spring Data
+ H2
+ Integration Tests
+ Unit tests
+ Logging
+ Swagger
+ AOP

# Testing:
________
Fot tests I used both Integrational and Unit tests. Also for endpoint documentation and test Swagger was used.

![image](https://github.com/akhulbay/financial-transactions/assets/117244670/ddf42646-ecc8-4cc3-bdb1-b7807db35662)

