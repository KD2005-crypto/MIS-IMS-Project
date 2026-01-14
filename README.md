# MIS & Invoicing Management System (MIS-IMS)

It is a **full-stack MIS & Invoicing Management System** with secure authentication, session handling, and role-based access.

## Tech Stack

## Backend
- Java
- Spring Boot
- Spring Security
- Hibernate / JPA
- MySQL
- Maven

## Frontend
- HTML
- CSS
- JavaScript
- Bootstrap 5

##  Features Implemented

## Authentication & Authorization
- Secure user login using **email and password**
- User registration
- Authentication checks using Spring Security

## Session Management
- Session is created after successful login
- User remains logged in during session
- Session invalidated on logout
- Unauthorized access blocked for protected pages

## Logout Functionality
- Secure logout endpoint
- Session destroyed on logout
- Prevents unauthorized access after logout

## Role-Based Access Control
- Admin
 Access to all functionalities
- Non-Admin / User
 Restricted access based on role

## Forgot & Reset Password
- Forgot password page
- Reset password flow (frontend + backend support)

## Dashboard
- User redirected to dashboard after successful login
- Dashboard accessible only after authentication

##  Project Structure

MIS-IMS-Project
│
├── backend
│ ├── src/main/java
│ │ └── com.mis.auth
│ │ ├── controller
│ │ ├── service
│ │ ├── repository
│ │ ├── entity
│ │ └── config
│ └── src/main/resources
│ └── application.properties
│
├── frontend
  ├── login.html
  ├── register.html
  ├── dashboard.html
  ├── forgot-password.html
  ├── reset-password.html
  └── js
   └── auth.js


# How to Run the Project

## Backend
- Start MySQL and create database:
  ```sql
  CREATE DATABASE mis_db;
  Update database credentials in:
  backend/src/main/resources/application.properties
Run backend:
mvn spring-boot:run
Backend runs on
http://localhost:8080

 Frontend

Open frontend files using Live Server or directly in browser

Start with:
login.html


Dhanshri Kalshetti
Internship Project – MIS-IMS  System




