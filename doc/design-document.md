## Introduction
### Purpose
The application (task manager) shall enable users to quickly and efficiently create, update, and manage their tasks.
### Target Audience
Anyone who wants to organize and keep track of simple tasks.
### Key Features
* The application will allow the user to easily manage their tasks and stay organized.
* The application will be easy to use and minimalistic
## Requirements
* The user shall be able to create tasks, update their status to complete/incomplete, and delete tasks.
* The user shall be able to filter tasks based on their status (complete/incomplete)
* The user should be able to search for tasks by name.
* The application should be responsive and should perform functions quickly.
## Architecture
### Components:
* **User Interface:** Responsible for displaying the user interface and handling user interaction. It will be implemented using Java Swing.
* **Task Manager:** Responsible for managing the tasks in the system. It will be implemented in Java.
* **Database:** Responsible for storing and retrieving the task data. It will be implemented using SQLite.
### Interactions:
* The user interface component will interact with the task manager component to create, edit, and delete tasks.
* The task manager component will interact with the database component to store and retrieve the task data.
### Technologies:
* **Programming language:** Java
* **Framework:** Java Swing
* **Database:** SQLite
### Deployment:
* The application will be deployed as a standalone executable file.
## User Interface Design
The user interface will be simple, clean, and easy to understand. One screen must hold all information necessary for managing tasks. Users may interact with the user interface via keyboard and mouse or hotkeys.
## Testing Plan
Test-driven development using JUnit Jupiter shall be practiced and all task manager code shall be covered by test cases. The user interface shall be tested manually, ensuring all visual and aural cues are functioning as expected.
## Deployment Plan
Once the application is complete, launch4j shall be used to wrap the completed jar and bundle the proper JRE into a windows executable.
## Maintenance Plan
* Bugs should be reported via the GitHub issues section.
* Feature updates should be documented and developed using GitHub projects, issues, and milestones.
## Appendix
