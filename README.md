# Banking Application API



## 📝 Overview

In this tutorial, we will learn how to build REST APIs for a simple Banking application using Spring Boot, Spring Data JPA (Hibernate), and MySQL database.

We'll create a simple version of a Banking App with basic functionalities: creating a bank account, fetching account details, and making a deposit/withdrawal.


# 📚 Table of Contents

## ✨ Features

- Create a new bank account
- Fetch All Acounts
- Fetch account details by ID
- Deposit money into an account
- Withdraw money from an account
- Delete an account

## 🛠 Technologies

- Java
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- JUnit
- MockMvc


## 🚀Getting Started

- Prerequisites
- Java 11 or later
- Maven
- MySQL

Installation
- Clone the repository
    bash git clone https://github.com/yourusername/banking-app.git
         Navigate to the project directory
  

  - cd banking-app
  - Update the application.properties file with your MySQL database credentials
  - properties
  - spring.datasource.url=jdbc:mysql://localhost:3306/yourdatabase
  - spring.datasource.username=yourusername
  - spring.datasource.password=yourpassword
  - spring.jpa.hibernate.ddl-auto=update
  - Build the project
  - mvn clean install
  - Run the application

  - mvn spring-boot:run

    
## 📬 API Endpoints

Create a new account :
  
- URL: /user
- Method: POST
- Request Body:
{
    "accountHolderName": "John Doe",
    "balance": 1000.0
}
- Response:
{
    "id": 1,
    "accountHolderName": "John Doe",
    "balance": 1000.0
}

Get account details :
  
- URL: /bank/{id}
- Method: GET
- Response:
{
    "id": 1,
    "accountHolderName": "John Doe",
    "balance": 1000.0
}

Deposit money :
- URL: /{id}/deposit
- Method: POST
- Request Body:
{
    "amount": 200.0
}
- Response:
{
    "id": 1,
    "accountHolderName": "John Doe",
    "balance": 1200.0

}

Withdraw money :
- URL: /{id}/withdraw
- Method: POST
- Request Body:
{
    "amount": 100.0
}
- Response:
{
    "id": 1,
    "accountHolderName": "John Doe",
    "balance": 1100.0
}


- Delete account :
  
URL: /{id}
Method: DELETE
Response:
- "Account deleted successfully"

## 🧪 Testing
- We use JUnit and MockMvc for testing the REST endpoints.
  - mvn test

## 🤝 Contributing
- Fork the repository.
- Create a new branch.
- Make your changes.
- Submit a pull request.

## 📜 License
- This project is licensed under the MIT License - see the LICENSE file for details.
