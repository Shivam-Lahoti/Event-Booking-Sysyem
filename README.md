# Event Booking Management System

This is a program about organizers making schedules for events and having customers book them.

There are two types of users in this program where users can register from:

- customers
- organizers

As an **event organizer**, you can customize your profile, check out all the events you have created, create a new event and modify tickets related to that event.

As a **customer**, you can view all available events, and choose the ones you'd like to participate in. You may also checkout the ones you have already booked and consider canceling if needed.

## Preparation

This project was developed using IntelliJ IDEA with Java Swing and incorporates additional dependencies, such as Apache Commons CSV, which will be automatically installed through Maven's poe.xml.

## Requirements

- Java 8 or higher
- Maven

## Installation

1. Clone the repository

```bash
git clone git@github.com:CSYE6200-Object-Oriented-DesignFall2023/final-project-final-group-17.git
```

2. Open the project in IntelliJ IDEA

3. Run the project

## Usage

### Login

- If you are a new user, you can register by clicking the "Register" button on the login page.
- If you are an existing user, you can login by entering your username and password.

### Organizer

- After logging in, you will be taken to the organizer dashboard, where you can view all the events you have created.
- You can create a new event by clicking the "Create Event" button.
- You can modify the tickets for an event by clicking the "Modify Tickets" button.
- You can customize your profile by clicking the "Profile" button.

### Customer

- After logging in, you will be taken to the customer dashboard, where you can view all the events you have booked.
- You can view all available events by clicking the "View Events" button.
- You can book an event by clicking the "Book Event" button.
- You can customize your profile by clicking the "Profile" button.

## Implementation

- Data Accessing

  No database was used for this project! Instead we accessed CSV files directly through APIs we designed.

  First there's the FileAccessUtility implementing FileAccessAPI, which allows us to perform basic operations towards files, such as creating, reading, updating and deleting data.

  Then based on that we created DataAccess and built data models based on this abstract class. This models allow us to operate each CSV table based on our needs.

  There's also services which is an integration of multiple data access models, which encapsulates and abstract those data accessing operations and makes it service related.

  To avoid conflicts, we added "synchronized" to many file accessing related methods.

- GUI

  We built GUI with Java Swing, based on which we implemented the user interface for our project. We created different frames and panels to display the various components of the application, such as buttons, text fields, tables, and labels.

  We also added event listeners to the buttons and other interactive components to handle user actions. For example, when the user clicks on a button, the corresponding event listener method is called to perform the necessary actions, such as reading or updating data.

  The GUI interacts with the data access layer by calling the appropriate methods in the data access models. For example, when the user clicks on a button to retrieve data, the GUI calls the corresponding method in the data access model, which in turn retrieves the data from the CSV file and returns it to the GUI for display.

  Overall, the GUI provides a user-friendly interface for interacting with the underlying data stored in CSV files. It allows users to view, add, update, and delete data using a familiar and intuitive graphical interface.

- Singleton

  We used singleton design pattern to ensure that only one instance of the data access models is created. It was used in FileAccessUtility and other data access models. It prevented multiple instances of the data access models from being created, which could lead to conflicts when accessing the CSV files.

- OOP

  We used OOP concepts to design the data access models. For example, the classes associated with the CSV data accessing.

  We also used OOP concepts to design the GUI. For example, we created a base class called BaseFrame, which contains common methods for creating the GUI. We then created subclasses for each frame, which inherit from the base class and implement the methods specific to that frame.

- Lambdas

  We used lambdas to implement the event listeners for the buttons and other interactive components in the GUI. For example, when the user clicks on a button, the corresponding event listener method is called to perform the necessary actions, such as reading or updating data.

  We also used lambdas and Predicate to filter the data in the tables. For example, when we only need records for one user, we can use a Predicate to filter the data and display only the records for that user.

- Exception Handling

  We used exception handling to handle errors that may occur when accessing the CSV files. For example, if the CSV file is corrupted or missing, we can catch the exception and display an error message to the user.

- Inner Classes

  We used inner classes to implement the event listeners for the buttons and other interactive components in the GUI. For example, when the user clicks on a button, the corresponding event listener method is called to perform the necessary actions, such as reading or updating data.

- Streams

  We used streams to filter the data in the tables. For example, when we only need records for one user, we can use a stream to filter the data and display only the records for that user.

- Anonymous Classes

  We used anonymous classes to implement the event listeners for the buttons and other interactive components in the GUI. For example, when the user clicks on a button, the corresponding event listener method is called to perform the necessary actions, such as reading or updating data.

## Contributors

File and Data Accessing, Login and Registration:

- Hanpeng Yang
  - File Accessing API
  - Data Accessing API
  - Data Accessing Models
- Yifan Yuan
  - Exceptions
  - Login and Registration GUI

Organizer Services and GUI:

- Anirudha Dudhasagare
  - Organizer Profile Management
  - Organizer Event Management
  - Organizer Event Creation
- Daryl Fernandes
  - Organizer Ticket Creation
  - Organizer Ticket Management
  - Organizer Ticket Type Management

Customer Services and GUI:

- Shivam Lahoti
  - Customer Event Viewing
  - Customer Event Booking
- Hariti Bhatia
  - Customer Profile Management
  - Customer Event Checking and Cancellation
