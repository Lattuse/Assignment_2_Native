package org.bonfire.lattuse

import java.util.Scanner

// interface
interface Identifiable {
    val id: Int
}

// data classes
data class Student(
    override val id: Int,
    var name: String,
    var age: Int,
    var courseId: Int?
) : Identifiable

data class Course(
    override val id: Int,
    var title: String
) : Identifiable

// manager class (oop)
class StudentManager {
    private val students = mutableListOf<Student>()
    private val courses = mutableListOf<Course>()

    private var nextStudentId = 1
    private var nextCourseId = 1

    // CRUD operations
    fun addStudent(name: String, age: Int, courseId: Int?) {
        if (students.any { it.name.lowercase() == name.lowercase() }) {
            println("Student with this name already exists.")
            return
        }

        val student = Student(nextStudentId++, name, age, courseId)
        students.add(student)
        println("Student added.")
    }

    fun editStudent(id: Int, newName: String?, newAge: Int?) {
        val student = students.find { it.id == id }
        if (student == null) {
            println("Student not found.")
            return
        }

        newName?.let { student.name = it }
        newAge?.let { student.age = it }

        println("Student updated.")
    }

    fun deleteStudent(id: Int) {
        val removed = students.removeIf { it.id == id }
        if (removed) println("Student deleted.")
        else println("Student not found.")
    }

    fun listStudents() {
        if (students.isEmpty()) {
            println("No students available.")
            return
        }

        students.forEach {
            println("ID: ${it.id}, Name: ${it.name}, Age: ${it.age}, Course: ${courseName(it.courseId)}")
        }
    }


    fun addCourse(title: String) {
        courses.add(Course(nextCourseId++, title))
        println("Course added.")
    }

    fun listCourses() {
        if (courses.isEmpty()) {
            println("No courses available.")
            return
        }
        courses.forEach { println("ID: ${it.id}, Title: ${it.title}") }
    }

    // functions with lambdas
    fun studentsOlderThan(age: Int) {
        val result = students.filter { it.age > age }
        println("Students older than $age:")
        result.forEach { println(it.name) }
    }

    fun sortStudentsByName() {
        val sorted = students.sortedBy { it.name.lowercase() }
        sorted.forEach { println("ID: ${it.id}, Name: ${it.name}") }
    }

    fun averageAge() {
        val avg = students.map { it.age }.average()
        if (students.isEmpty()) println("No students to calculate average.")
        else println("Average age: %.2f".format(avg))
    }

    // helpers
    private fun courseName(courseId: Int?): String {
        return courseId?.let { id ->
            courses.find { it.id == id }?.title ?: "Unknown"
        } ?: "None"
    }
}


fun main() {
    val scanner = Scanner(System.`in`)
    val manager = StudentManager()
    var running = true

    while (running) {
        println("\n=== Student Management System ===")
        println("1. Add student")
        println("2. Edit student")
        println("3. Delete student")
        println("4. List students")
        println("5. Add course")
        println("6. List courses")
        println("7. Students older than X")
        println("8. Sort students by name")
        println("9. Average student age")
        println("0. Exit")
        print("Choose option: ")

        when (scanner.nextLine()) {
            "1" -> {
                print("Name: ")
                val name = scanner.nextLine().trim()

                print("Age: ")
                val age = scanner.nextLine().toIntOrNull()

                print("Course ID (optional): ")
                val courseId = scanner.nextLine().toIntOrNull()

                if (age == null) println("Invalid age.")
                else manager.addStudent(name, age, courseId)
            }

            "2" -> {
                print("Student ID: ")
                val id = scanner.nextLine().toIntOrNull()

                print("New name (leave empty): ")
                val name = scanner.nextLine().trim().ifBlank { null }

                print("New age (leave empty): ")
                val age = scanner.nextLine().toIntOrNull()

                id?.let { manager.editStudent(it, name, age) } ?: println("Invalid ID.")
            }

            "3" -> {
                print("Student ID: ")
                scanner.nextLine().toIntOrNull()
                    ?.let { manager.deleteStudent(it) }
                    ?: println("Invalid ID.")
            }

            "4" -> manager.listStudents()
            "5" -> {
                print("Course title: ")
                manager.addCourse(scanner.nextLine().trim())
            }
            "6" -> manager.listCourses()
            "7" -> {
                print("Age: ")
                scanner.nextLine().toIntOrNull()
                    ?.let { manager.studentsOlderThan(it) }
                    ?: println("Invalid age.")
            }
            "8" -> manager.sortStudentsByName()
            "9" -> manager.averageAge()
            "0" -> {
                println("Saubol(goodbye)!")
                running = false
            }
            else -> println("Invalid option.")
        }
    }
}
