# 🍋 Citronix - Lemon Farm Management System

[![GitHub repo](https://img.shields.io/badge/GitHub-Citronix-green.svg)](https://github.com/Sahtani/Citronix)
[![Design](https://img.shields.io/badge/Design-LucidChart-blue.svg)](https://lucid.app/lucidchart/e84f57eb-617f-4b6c-a518-b15c5bf03c96/edit)

## 📝 Project Overview
Citronix is a comprehensive lemon farm management system designed to help farmers track and optimize their production from planting to sales. Built using Domain-Driven Design principles, the system provides robust management of farms, fields, trees, harvests, and sales.

## 🏗 Domain-Driven Design Architecture

### Core Domains
1. **Farm Management Domain**
    - Aggregate Root: Farm
    - Entities: Field ,Tree
    - 
2. **Harvest Domain**
    - Aggregate Root: Harvest
    - Entities: HarvestDetail
    - Value Objects: Season

3. **Sales Domain**
    - Aggregate Root: Sale

### Bounded Contexts
- Farm Management Context
- Production Context
- Sales & Revenue Context

## ✨ Key Features

### Farm Management
- CRUD operations for farms (name, location, area, creation date)
- Multi-criteria search using Criteria Builder
- Maximum 10 fields per farm

### Field Management
- Field-to-farm association with area constraints
- Area validations:
    - Minimum: 0.1 hectare (1,000 m²)
    - Maximum: 50% of total farm area
    - Sum of field areas < total farm area

### Tree Management
- Tree lifecycle tracking (planting date, age, location)
- Age-based productivity:
    - Young (< 3 years): 2.5 kg/season
    - Mature (3-10 years): 12 kg/season
    - Old (> 10 years): 20 kg/season
- Constraints:
    - Maximum 100 trees/hectare
    - Productive lifespan: 20 years
    - Planting window: March to May only

### Harvest Management
- One harvest per season (quarterly)
- Detailed tree-level tracking
- Date and quantity recording
- Single harvest per tree per season

### HarvestDetails Management
- Detailed tree-level tracking
- Date and quantity recording

### Sales Management
- Transaction tracking
- Automated revenue calculation (quantity × unit price)
- Harvest association

## 🛠 Technical Stack

### Backend
- Java 21
- Spring Boot
- Spring Data JPA
- Spring Validation
- Lombok
- MapStruct

### Development Tools
- Maven
- JUnit 5
- Mockito
- Git

## 🏛 Project Structure (DDD-based)
```
src/
├── main/
│   ├── java/
│   │   └── com.youcodelab.citronix
├── common
│   ├── config
│   ├── controller
│   ├── exception
│   ├── mapper
│   └── service
├── farm
│   ├── application
│   │   ├── dto
│   │   ├── mapper
│   │   └── service
│   ├── domain
│   └── infrastructure
├── harvest
│   ├── application
│   ├── domain
│   └── infrastructure
├── sale
│   ├── application
│   ├── domain
│   └── infrastructure
└── CitronixApplication

│   └── resources/
└── test/
```

## 🚀 Getting Started

### Prerequisites
- Java JDK 22
- Maven
- Your favorite IDE (IntelliJ IDEA recommended)
- Git

### Installation
```bash
# Clone the repository
git clone https://github.com/Sahtani/Citronix.git

# Navigate to project directory
cd Citronix

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

## 🔍 Domain Rules and Constraints

### Business Rules
1. Field area validations
2. Tree density limits
3. Planting period restrictions
4. Seasonal harvest management
5. Tree age and productivity tracking

### Technical Validations
1. Comprehensive unit testing
2. Input data validation
3. Exception handling
4. API documentation
5. SOLID principles adherence

## 🧪 Testing
```bash
# Run tests
mvn test

# Run tests with coverage
mvn test verify
```

## 📖 API Documentation
API documentation will be available at `http://localhost:8080/swagger-ui.html` when running the application.

## 🤝 Contributing
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 🔗 Resources
- [Project Repository](https://github.com/Sahtani/Citronix)
- [Design Documentation](https://lucid.app/lucidchart/e84f57eb-617f-4b6c-a518-b15c5bf03c96/edit)
