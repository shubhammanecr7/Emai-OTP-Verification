# Spring Boot Email OTP Verification App

This is a simple Spring Boot application for email OTP (One-Time Password) verification. It allows users to register, send OTP to their email, and verify their account using the OTP.

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Setup](#setup)
4. [Usage](#usage)
5. [Endpoints](#endpoints)
6. [Dependencies](#dependencies)

## Introduction

This application provides a RESTful API for user registration and email OTP verification. It's built using Spring Boot framework and uses Java for backend development.

## Features

- User registration
- Sending OTP to user's email
- Verifying user using OTP

## Setup

1. Clone the repository to your local machine.
2. Make sure you have Java and Maven installed.
3. Configure your email SMTP settings in `application.properties`.
4. Build the project using Maven.
5. Run the application.

## Usage

- Register a new user using the `/auth/register` endpoint. An OTP will be sent to the registered email internally during registration.
- Alternatively, you can also test the email sending functionality separately using the `/auth/sendOtp/{email}` endpoint. This endpoint is useful for testing if the email sending is working properly.
- Verify the user using the `/auth/verify` endpoint by providing the email and OTP.

## Endpoints

- **POST /auth/register**: Register a new user.
  - Request Body: JSON object containing user information (name, email, etc.).
  - Response: JSON object containing registration status.

- **POST /auth/sendOtp/{email}**: Send OTP to the specified email for testing purposes.
  - Path Variable: Email address of the user.
  - Response: Success message indicating OTP sent successfully.

- **POST /auth/verify**: Verify user using OTP.
  - Request Parameters: Email and OTP.
  - Response: Success message indicating user verification status.

## Dependencies

- Spring Boot Starter Web
- Lombok
- spring-boot-starter-data-jpa
- mysql-connector-j
- spring-boot-starter-mail

## Contributing

Contributions are welcome! Feel free to fork the repository and submit pull requests for new features, bug fixes, or improvements.

---
