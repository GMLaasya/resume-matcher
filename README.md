**AI-Powered Resume and Job Description Matcher**

**Project Overview**

A full-stack web application that helps job seekers compare their resumes with job descriptions, identify missing skills, evaluate resume relevance, and receive recommendations for improvement.

**Features**

- Resume upload and PDF text extraction.
- Resume and job-description matching.
- ATS score generation.
- Skill-gap analysis to identify potential missing skills.
- Rule-based recommendations for skill improvement.
- React frontend integrated with Spring Boot REST APIs.
- MySQL database integration.

**Tech Stack**

- Frontend: React.js, JavaScript, HTML, CSS
- Backend: Java, Spring Boot
- Database: MySQL
- ORM: Spring Data JPA, Hibernate
- PDF Processing: Apache PDFBox
- Build Tool: Maven
- Version Control: Git and GitHub

**Application Workflow**

1. The user uploads a resume and provides a job description.
2. The Spring Boot backend processes the resume and extracts text from the PDF.
3. The application compares the resume information with the job requirements.
4. The system generates matching results and an ATS-related score.
5. The skill-gap module identifies potential missing skills.
6. The recommendation module provides suggestions based on the identified gaps.
7. Results are displayed through the React frontend.



**Installation and Setup**

**Prerequisites:** Java 17, Node.js, npm, and MySQL.

1. Clone the repository


git clone https://github.com/GMLaasya/resume-matcher.git
cd resume-matcher


2. Create the database

CREATE DATABASE resume_analyzer;


Configure the MySQL connection in `src/main/resources/application.properties` and set your database password using an environment variable.

3. Run the backend


$env:DB_PASSWORD="your_local_database_password"
$env:SPRING_DATASOURCE_PASSWORD=$env:DB_PASSWORD
.\mvnw.cmd spring-boot:run


 4. Run the frontend

Open a separate terminal:


cd frontend
npm install
npm run dev


Open the local URL displayed by Vite, normally `http://localhost:5173`.

**Author**

**G. M. Laasya**

- GitHub: [GMLaasya](https://github.com/GMLaasya)
- LinkedIn: [Profile](https://www.linkedin.com/in/g-m-laasya-083162293/)
