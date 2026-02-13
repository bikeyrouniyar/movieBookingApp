🎬 Movie Booking Platform

A simplified backend implementation of a movie ticket booking platform built using Java, Spring Boot, and PostgreSQL.

This project demonstrates solution thinking, architecture design, domain modeling, and clean backend implementation.

Tech Stack

Java 17

Spring Boot

Spring Data JPA

PostgreSQL

Maven

Objectives Covered:

1️⃣ Theatre Partner Onboarding

The platform enables theatre partners to:

Register on the platform

Add theatres in different cities

Add screens to theatres

Create movie shows

Digitally manage inventory

This allows theatre owners to go digital and reach a larger customer base.

2️⃣ End Customer Experience

Customers can:

Browse movies by city

View available shows

See seat availability

Book tickets in advance

This ensures a seamless and simple booking experience.

🏗 High-Level Architecture

The application follows a layered monolithic architecture:

Client (Postman / UI)
↓
Controller Layer
↓
Service Layer (Business Logic)
↓
Repository Layer (JPA)
↓
PostgreSQL

📌 Layer Responsibilities
Controller Layer

Handles HTTP requests

Validates input

Delegates to services

Service Layer

Contains business logic

Handles seat allocation

Manages booking transactions

Repository Layer

Handles database interaction

Uses Spring Data JPA

📦 Domain Model
Partner

Represents theatre owners.

id

name

email

contactNumber

active

Theatre

Represents a theatre location.

id

name

city

partner (ManyToOne)

Screen

Each theatre can have multiple screens.

id

name

totalSeats

theatre (ManyToOne)

Movie

id

name

language

genre

durationMinutes

Show

id

showDate

showTime

price

movie (ManyToOne)

screen (ManyToOne)

When a show is created, seats are automatically generated.

Seat

id

seatNumber

status (AVAILABLE / BOOKED)

show (ManyToOne)

Booking

id

customerName

totalAmount

show (ManyToOne)

🔌 API Endpoints
Partner APIs

POST /partners

{
"name": "PVR Group",
"email": "pvr@gmail.com",
"contactNumber": "9999999999"
}

Theatre APIs

POST /theatres

{
"name": "PVR Phoenix Mall",
"city": "Bangalore",
"partner": { "id": 1 }
}

Screen APIs

POST /screens

{
"name": "Screen 1",
"totalSeats": 50,
"theatre": { "id": 1 }
}

Movie APIs

POST /movies

{
"name": "Inception",
"language": "English",
"genre": "Sci-Fi",
"durationMinutes": 150
}

Show APIs

POST /shows

{
"movie": { "id": 1 },
"screen": { "id": 1 },
"showDate": "2026-02-15",
"showTime": "14:30:00",
"price": 300
}

Booking API

POST /bookings

{
"customerName": "Rahul",
"show": { "id": 1 },
"seatNumbers": ["S1", "S2"]
}

🧠 Design Decisions
1️⃣ Layered Architecture

Ensures separation of concerns and maintainability.

2️⃣ Automatic Seat Allocation

Seats are generated when a show is created based on screen capacity.

3️⃣ Transactional Booking

Booking operations are wrapped inside transactions to avoid partial updates.

skipped to keep the solution focused and simple.

📈 Non-Functional Considerations
Scalability

Can scale horizontally using load balancer

Connection pooling via HikariCP

Redis can be added for caching browse APIs

Availability

Can be containerized using Docker

Can be deployed on Kubernetes

DB replication for failover

Security (Design-Level)

JWT-based authentication

Role-based access (PARTNER / CUSTOMER)

Input validation and sanitization

Payment Integration (Design-Level)

Booking flow can be extended as:

Create temporary booking

Lock seats

Redirect to payment gateway

Confirm booking on payment success

Release seats on failure

▶️ How to Run

Install PostgreSQL

Create database:

create database movie_booking;


Update application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/movie_booking
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update


Run:

mvn spring-boot:run


Server runs at:

http://localhost:8080
