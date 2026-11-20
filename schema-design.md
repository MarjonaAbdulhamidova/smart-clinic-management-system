# Smart Clinic Management System – Database Schema Design

## 1. Doctors Table

| Field Name | Data Type | Constraints |
|---|---|---|
| id | BIGINT | Primary Key, Auto Increment |
| name | VARCHAR(100) | NOT NULL |
| email | VARCHAR(100) | UNIQUE, NOT NULL |
| password | VARCHAR(255) | NOT NULL |
| specialty | VARCHAR(100) | NOT NULL |
| phone | VARCHAR(20) | |
| available_times | TEXT | |

## 2. Patients Table

| Field Name | Data Type | Constraints |
|---|---|---|
| id | BIGINT | Primary Key, Auto Increment |
| name | VARCHAR(100) | NOT NULL |
| email | VARCHAR(100) | UNIQUE, NOT NULL |
| password | VARCHAR(255) | NOT NULL |
| phone | VARCHAR(20) | |
| date_of_birth | DATE | |
| address | VARCHAR(255) | |

## 3. Appointments Table

| Field Name | Data Type | Constraints |
|---|---|---|
| id | BIGINT | Primary Key, Auto Increment |
| doctor_id | BIGINT | Foreign Key references Doctors(id) |
| patient_id | BIGINT | Foreign Key references Patients(id) |
| appointment_time | DATETIME | NOT NULL |
| status | VARCHAR(30) | NOT NULL |
| reason | VARCHAR(255) | |

## 4. Prescriptions Table

| Field Name | Data Type | Constraints |
|---|---|---|
| id | BIGINT | Primary Key, Auto Increment |
| appointment_id | BIGINT | Foreign Key references Appointments(id) |
| doctor_id | BIGINT | Foreign Key references Doctors(id) |
| patient_id | BIGINT | Foreign Key references Patients(id) |
| medication | VARCHAR(255) | NOT NULL |
| dosage | VARCHAR(100) | NOT NULL |
| instructions | TEXT | |
| prescribed_date | DATE | NOT NULL |

## 5. Relationships

- One Doctor can have many Appointments.
- One Patient can have many Appointments.
- One Appointment belongs to one Doctor and one Patient.
- One Appointment can have one or more Prescriptions.
- Each Prescription is associated with a Doctor and a Patient.

## 6. Database

Database Management System: MySQL.

The schema uses primary keys, foreign keys, unique constraints, and appropriate data types to maintain data integrity and support clinic operations.
