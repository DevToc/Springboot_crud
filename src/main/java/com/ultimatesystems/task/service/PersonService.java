package com.ultimatesystems.task.service;

import java.util.List;

import com.ultimatesystems.task.entity.Pagination;
import com.ultimatesystems.task.entity.Person;
import com.ultimatesystems.task.entity.Student;
import com.ultimatesystems.task.entity.Teacher;

public interface PersonService {

    String dataValidation(Person person);

    // Student

    List<Student> getStudents(Pagination pagination);

    List<Teacher> getMyTeachers(Long id);

    String addTeacher(Long id, Long teacherId);

    String deleteTeacher(Long id, Long teacherId);

    List<Student> searchStudent(String keyword, String name);

    Student findStudent(Long id);

    String createStudent(Student student);

    String deleteStudent(Long id);

    String updateStudent(Long id, Student student);

    // Teacher

    List<Teacher> getTeachers(Pagination pagination);

    List<Teacher> searchTeacher(String keyword, String name);

    List<Student> getMyStudents(Long id);

    String addStudent(Long id, Long studentId);

    String deleteStudent(Long id, Long studentId);

    Teacher findTeacher(Long id);

    String createTeacher(Teacher teacher);

    String deleteTeacher(Long id);

    String updateTeacher(Long id, Teacher student);
}