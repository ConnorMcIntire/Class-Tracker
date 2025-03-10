Overview
The Course Organizer application is a tool designed to help users manage their college courses. The application allows users to add, update, and view course details such as course prefix, number, semester, and notes.

The project is built using Maven, with the user interface (UI) designed using FXML. The application provides the following functionality:

Add a new course
Update notes for an existing course
View courses by semester or department
View the most recent semester
Features
Home Page & Course Information Page
The UI consists of a Home Page and a Course Information Page for adding and updating course details.
Navigation is implemented so that clicking "Add Course" or "Update Course" brings up the Course Information Page.
The Course Information Page includes "Save" and "Cancel" buttons that return the user to the Home Page.
Add a Course
Courses can be added with required fields for course prefix, number, and semester.
The system enforces uniqueness for courses based on the combination of prefix, number, and semester.
Update a Course
Users can update the "Notes" section of a selected course.
The Save and Cancel buttons are available to save or discard changes.
Department Information
The application displays courses by department.
The department dropdown is populated with course prefixes.
Semester Information
The most recent semester’s courses are listed by default.
The semester dropdown is populated with available semesters from courses in the system.
