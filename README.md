# AI Healthy Meals

Spring Boot Web Application – AI Healthy Meals
Repository: `CS3220-AI-Application` 

## Table of Contents

1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [Getting Started](#getting-started)

   * Prerequisites
   * Installation & Setup
   * Running the application
5. [Usage](#usage)
6. [Project Structure](#project-structure)
7. [Future Improvements](#future-improvements)
8. [Contributing](#contributing)
9. [License](#license)

## Project Overview

The AI Healthy Meals application is a web-based project built using the Spring Boot framework in Java. Its goal is to deliver a user-friendly interface for generating or suggesting healthy meal options using AI techniques (or machine learning / heuristic logic), helping users plan meals that align with nutritional goals and dietary preferences.

## Features

* Web UI for users to input dietary preferences (e.g., vegetarian, low-carb, allergies)
* AI/algorithm-driven meal suggestion engine
* Nutritional information display for each suggested meal
* Ability to save or bookmark favorite meals (optional enhancement)
* Responsive UI for desktop and mobile (via Spring MVC / templates or REST + frontend)

## Technologies Used

* Java
* Spring Boot
* Maven (project build & dependency management)
* Spring MVC / REST Controllers
* Front-end technologies: HTML, CSS, Bootstrap (or other UI framework)

## Getting Started

### Prerequisites

* Java JDK (version 17 or compatible)
* Maven
* (Optional) A running database if you’re not using in-memory defaults
* Git (to clone the repository)

### Installation & Setup

```bash
git clone https://github.com/herrera-o/CS3220-AI-Application.git  
cd CS3220-AI-Application  
# Optionally modify application.properties (e.g., DB credentials)  
mvn clean install  
```

### Running the Application

```bash
mvn spring-boot:run  
```

Once started, open your browser and navigate to:
[http://localhost:8080/](http://localhost:8080/)
(Adjust port if configured differently)

## Usage


## Project Structure

```
CS3220-AI-Application/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/      (controllers, services, models, ai-engine)
│   │   └── resources/ (application.properties, templates/static content)
│   └── test/
├── pom.xml
└── mvnw, mvnw.cmd, .gitignore, etc.
```

* `controllers/` – handle HTTP requests
* `services/` – business logic, including AI/meal generation engine
* `models/` – domain objects e.g., Meal, NutritionInfo, UserPreferences
* `resources/templates/` – UI templates (JTE)
* `resources/static/` – CSS, JS, images

## Future Improvements


## Contributing

Contributions are welcome! If you’d like to contribute:

1. Fork the repository
2. Create a new branch (`feature/YourFeature`)
3. Commit your changes and push to your branch
4. Submit a pull request describing your changes and motivation

Please adhere to coding standards, include meaningful commit messages, and add test coverage where applicable.

## License

