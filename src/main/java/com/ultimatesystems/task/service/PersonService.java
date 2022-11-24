package com.ultimatesystems.task.service;

import java.util.List;

import com.ultimatesystems.task.entity.Person;
import com.ultimatesystems.task.entity.Student;
import com.ultimatesystems.task.entity.Teacher;

public interface PersonService {

    String dataValidation(Person person);

    // Student

    List<Student> getStudents();

    String createStudent(Student student);

    String deleteStudent(Long id);

    String updateStudent(Long id, Student student);

    // Teacher

    List<Teacher> getTeachers();

    String createTeacher(Teacher teacher);

    String deleteTeacher(Long id);

    String updateTeacher(Long id, Teacher student);
}