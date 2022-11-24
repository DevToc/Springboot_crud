package com.ultimatesystems.task.service;

import com.ultimatesystems.task.dao.StudentRepository;
import com.ultimatesystems.task.dao.TeacherRepository;
import com.ultimatesystems.task.entity.Person;
import com.ultimatesystems.task.entity.Student;
import com.ultimatesystems.task.entity.Teacher;

import java.util.List;
import java.util.Optional;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

    public PersonServiceImpl(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public String dataValidation(Person person) {

        EmailValidator emailValidator = EmailValidator.getInstance();

        if (emailValidator.isValid(person.getEmail())
                && person.getFirstName().length() > 2 && person.getFirstName().chars().allMatch(Character::isLetter)
                && person.getAge() > 18) {

            return "valid data";
        } else {

            return "invalid data";
        }
    }

    @Override
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public String createTeacher(Teacher teacher) {
        if (dataValidation(teacher) == "invalid data") {
            return dataValidation(teacher);
        }
        try {
            teacherRepository.save(teacher);
        } catch (Exception e) {
            return e.toString();
        }
        return "Saved Successfully";
    }

    @Override
    public String deleteTeacher(Long id) {
        try {
            teacherRepository.deleteById(id);
        } catch (Exception e) {
            return e.toString();
        }
        return "Deleted Successfully";
    }

    @Override
    public String updateTeacher(Long id, Teacher teacher) {
        if (dataValidation(teacher) == "invalid data") {
            return dataValidation(teacher);
        }
        Optional<Teacher> tch = teacherRepository.findById(id);
        if (tch.isEmpty()) {
            return "Can't find student";
        }

        tch.get().setFirstName(teacher.getFirstName());
        tch.get().setLastName(teacher.getLastName());
        tch.get().setAge(teacher.getAge());
        tch.get().setEmail(teacher.getEmail());
        tch.get().setCourse(teacher.getCourse());

        try {
            teacherRepository.save(tch.get());
        } catch (Exception e) {
            return e.toString();
        }
        return "Updated Successfully";
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public String createStudent(Student student) {
        if (dataValidation(student) == "invalid data") {
            return dataValidation(student);
        }
        try {
            studentRepository.save(student);
        } catch (Exception e) {
            return e.toString();
        }
        return "Saved Successfully";
    }

    @Override
    public String deleteStudent(Long id) {
        try {
            studentRepository.deleteById(id);
        } catch (Exception e) {
            return e.toString();
        }
        return "Deleted Successfully";
    }

    @Override
    public String updateStudent(Long id, Student student) {
        if (dataValidation(student) == "invalid data") {
            return dataValidation(student);
        }
        Optional<Student> std = studentRepository.findById(id);
        if (std.isEmpty()) {
            return "Can't find student";
        }

        std.get().setFirstName(student.getFirstName());
        std.get().setLastName(student.getLastName());
        std.get().setAge(student.getAge());
        std.get().setEmail(student.getEmail());
        std.get().setDegreeProgramme(student.getDegreeProgramme());

        try {
            studentRepository.save(std.get());
        } catch (Exception e) {
            return e.toString();
        }
        return "Updated Successfully";
    }

}