## Overview
The **Course Organizer** application is a tool designed to help users manage their college courses. The application allows users to:

- **Add** a new course
- **Update** notes for an existing course
- **View** courses by semester or department
- **View** the most recent semester

The project is built using **Maven**, with the user interface (UI) designed using **FXML**.

---

## Features

### 1. **Home Page & Course Information Page**
The UI consists of:
- **Home Page**: Displays a list of courses, semester, and department filters.
- **Course Information Page**: Allows users to add or update course details.

#### Navigation:
- Clicking **"Add Course"** or **"Update Course"** opens the Course Information Page.
- The **"Save"** and **"Cancel"** buttons on the Course Information Page return the user to the Home Page.

---

### 2. **Add a Course**
- **Required fields**: Course prefix, number, and semester.
- The system enforces **uniqueness** for courses based on the combination of:
  - Course prefix
  - Course number
  - Semester

---

### 3. **Update a Course**
- Users can **update the "Notes" section** of a selected course.
- **Save** or **Cancel** changes:
  - **Save** to apply changes.
  - **Cancel** to discard changes and return to the Home Page.

---

### 4. **Department Information**
- Courses can be filtered by **department**.
- The **department dropdown** is populated with course prefixes from added courses.

---

### 5. **Semester Information**
- The **most recent semester's courses** are listed by default.
- The **semester dropdown** is populated with available semesters based on the courses added to the system.

---
