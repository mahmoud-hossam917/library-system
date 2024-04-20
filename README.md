# Library Management System

This project is a Library Management System API developed using Spring Boot. It is designed to help librarians manage books, patrons, and borrowing records effectively.

## Getting Started

These instructions will guide you on how to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Before you begin, ensure you have the following installed on your system:

- **Java JDK 11 or newer** - Required to run the Java application.
- **Maven** - Used for dependency management and running the application.
- **An IDE of your choice** - Such as IntelliJ IDEA, Eclipse, or VS Code for code editing and management.

### Installation

Follow these steps to set up your development environment:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/mahmoud-hossam917/library-system.git
   ```
2. **Navigate to the project directory**:
   ```bash
   cd library-management-system
   ```
3. **Install dependencies**:
   ```bash
   mvn install
   ```
4. **Set up PostgreSQL**:
   - Install the PostgreSQL database, create a database named `library`, and then configure your connection settings:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/library
     spring.datasource.username=postgres
     spring.datasource.password=password
     ```
5. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```
   The application should now be running on [http://localhost:4040](http://localhost:4040).

## Usage

To interact with the API, you can use the following HTTP requests:

### Books

- **List all books**:
  ```http
  GET /api/books
  ```

## Built With

- **Spring Boot** - The web framework used.
- **Maven** - Dependency management.
- **PostgreSQL** - Used as the development database.

## Authors

- **Mahmoud Hossam**

---