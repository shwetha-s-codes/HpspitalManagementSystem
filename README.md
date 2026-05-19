# Hospital Management System

A comprehensive Java-based Hospital Management System built with Spring Boot, designed to streamline hospital operations, patient management, and medical staff coordination.

## 🏥 Features

- **Patient Management** - Register, track, and manage patient records
- **Doctor Management** - Schedule and manage medical staff
- **Appointment Scheduling** - Book and manage patient appointments
- **User Authentication & Authorization** - Secure access control with JWT tokens
- **Email Notifications** - Automated email alerts using Mailtrap
- **Message Queue** - Asynchronous processing with RabbitMQ for reliable message handling
- **Data Validation** - Comprehensive input validation using Spring Validation
- **Security** - Spring Security with JWT-based authentication

## 🚀 Coming Soon - ML Integration

An **ML-based Neonatal RDS (Respiratory Distress Syndrome) Prediction Model** is currently being developed locally and will be integrated with this project. This advanced predictive model will help in early detection and assessment of respiratory distress in neonates, improving patient outcomes and clinical decision-making.

**Status**: Under development - Awaiting completion of paperwork and regulatory compliance related to the ML model before public release and integration.

---

## 🛠 Tech Stack

| Component | Technology |
|-----------|-----------|
| **Backend Framework** | Spring Boot 4.0.3 |
| **Language** | Java 21 |
| **ORM** | Spring Data JPA |
| **Database** | MySQL |
| **Authentication** | JWT (JSON Web Token) |
| **Message Queue** | RabbitMQ |
| **Email Service** | Mailtrap |
| **Security** | Spring Security |
| **Build Tool** | Maven |
| **Project Utilities** | Lombok |

## 📋 Prerequisites

- **Java 21** or higher
- **Maven 3.6+**
- **MySQL 8.0+**
- **RabbitMQ** (for message queue operations)
- **Git**

## 💻 Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/shwetha-s-codes/HopspitalManagementSystem.git
cd HopspitalManagementSystem
```

### 2. Navigate to Project Directory
```bash
cd HospitalManagementSystem
```

### 3. Install Dependencies
```bash
mvn clean install
```

### 4. Configure Environment Variables

Create a `.env` file in the project root with the following configuration:

```env
# Database Configuration
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/hospital_management
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=your_password
SPRING_JPA_HIBERNATE_DDL_AUTO=update

# Server Configuration
SERVER_PORT=8080

# JWT Configuration
JWT_SECRET_KEY=your_jwt_secret_key_here
JWT_EXPIRATION=3600000

# RabbitMQ Configuration
SPRING_RABBITMQ_HOST=localhost
SPRING_RABBITMQ_PORT=5672
SPRING_RABBITMQ_USERNAME=guest
SPRING_RABBITMQ_PASSWORD=guest

# Email Configuration (Mailtrap)
SPRING_MAIL_HOST=smtp.mailtrap.io
SPRING_MAIL_PORT=2525
SPRING_MAIL_USERNAME=your_mailtrap_username
SPRING_MAIL_PASSWORD=your_mailtrap_password
SPRING_MAIL_FROM=noreply@hospitalmanagement.com
```

### 5. Create Database
```sql
CREATE DATABASE hospital_management;
```

## 📁 Project Structure

```
HospitalManagementSystem/
├── src/
│   ├── main/
│   │   ├── java/com/Project/HospitalManagementSystem/
│   │   │   ├── controller/          # REST API Controllers
│   │   │   ├── service/             # Business Logic
│   │   │   ├── repository/          # Data Access Layer
│   │   │   ├── entity/              # JPA Entities
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   ├── security/            # JWT & Security Config
│   │   │   ├── config/              # Application Configuration
│   │   │   ├── exception/           # Custom Exceptions
│   │   │   └── HospitalManagementSystemApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── .env
│   └── test/                        # Unit Tests
├── pom.xml                          # Maven Dependencies
└── README.md
```

## 🔌 API Documentation

### Authentication
- **POST** `/api/auth/register` - Register a new user
- **POST** `/api/auth/login` - User login
- **POST** `/api/auth/logout` - User logout

### Patients
- **GET** `/api/patients` - Get all patients
- **GET** `/api/patients/{id}` - Get patient by ID
- **POST** `/api/patients` - Create new patient
- **PUT** `/api/patients/{id}` - Update patient details
- **DELETE** `/api/patients/{id}` - Delete patient

### Doctors
- **GET** `/api/doctors` - Get all doctors
- **GET** `/api/doctors/{id}` - Get doctor by ID
- **POST** `/api/doctors` - Add new doctor
- **PUT** `/api/doctors/{id}` - Update doctor details
- **DELETE** `/api/doctors/{id}` - Remove doctor

### Appointments
- **GET** `/api/appointments` - Get all appointments
- **POST** `/api/appointments` - Book new appointment
- **PUT** `/api/appointments/{id}` - Update appointment
- **DELETE** `/api/appointments/{id}` - Cancel appointment

## 🗄️ Database Schema Overview

The system uses the following main entities:
- **User** - System users with authentication
- **Patient** - Patient information and records
- **Doctor** - Medical staff details
- **Appointment** - Appointment scheduling

## ▶️ Running the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 🧪 Testing

Run the test suite:

```bash
mvn test
```

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a Pull Request

## �� License

This project is currently unlicensed. Please contact the repository owner for licensing information.

## 👨‍💼 Author

**Shwetha S**  
GitHub: [@shwetha-s-codes](https://github.com/shwetha-s-codes)

---

**Last Updated**: May 2026
