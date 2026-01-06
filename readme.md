## Student Management System (Kotlin)

### How to run:
1. Download ZIP project or clone the project using command below in terminal (git required)

```git clone https://github.com/Lattuse/Assignment_2_Native.git```


2. Open project in Intellij IDEA / other IDE3
3. Run the main() function

### Description:
This console-based application manages students and courses using object-oriented
principles. It supports CRUD operations, uses Kotlin data classes, interfaces,
higher-order functions, and lambdas for filtering, sorting, and statistics.

### Design decisions:
- Student is a data class for concise modeling.
- StudentManager encapsulates all logic.
- Lambdas are used for sorting, filtering, and aggregation.
- Null safety prevents runtime crashes.

### Features:
- Add, edit, delete, list students
- Add and list courses
- Sort students by name
- Filter students by age
- Calculate average age
