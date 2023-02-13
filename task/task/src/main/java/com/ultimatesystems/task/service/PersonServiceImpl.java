package com.ultimatesystems.task.service;

import com.ultimatesystems.task.dao.StudentRepository;
import com.ultimatesystems.task.dao.TeacherRepository;
import com.ultimatesystems.task.entity.Pagination;
import com.ultimatesystems.task.entity.Person;
import com.ultimatesystems.task.entity.Student;
import com.ultimatesystems.task.entity.Teacher;

import java.util.List;
import java.util.Optional;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Teacher> getTeachers(Pagination pagination) {

        if (pagination.pageNo == 0) {

            if (pagination.sortKey.equals(""))
                return teacherRepository.findAll();
            else
                return teacherRepository.findAll(Sort.by(pagination.sortKey));
        } else {
            if (pagination.pageSize == 0) {

                if (pagination.sortKey.equals("")) {

                    Pageable paging = PageRequest.of(pagination.pageNo - 1, 5);
                    Page<Teacher> pagedTeachers = teacherRepository.findAll(paging);
                    return pagedTeachers.toList();
                } else {

                    Pageable paging = PageRequest.of(pagination.pageNo - 1, 5, Sort.by(pagination.sortKey));
                    Page<Teacher> pagedTeachers = teacherRepository.findAll(paging);
                    return pagedTeachers.toList();
                }
            } else {
                if (pagination.sortKey.equals("")) {

                    Pageable paging = PageRequest.of(pagination.pageNo - 1, pagination.pageSize);
                    Page<Teacher> pagedTeachers = teacherRepository.findAll(paging);
                    return pagedTeachers.toList();
                } else {

                    Pageable paging = PageRequest.of(pagination.pageNo - 1, pagination.pageSize,
                            Sort.by(pagination.sortKey));
                    Page<Teacher> pagedTeachers = teacherRepository.findAll(paging);
                    return pagedTeachers.toList();
                }
            }
        }

    }

    @Override
    public List<Teacher> searchTeacher(String keyword, String name) {
        if (keyword.equals("firstname")) {

            return teacherRepository.findByFirstName(name);
        } else
            return teacherRepository.findByLastName(name);
    }

    @Override

    public Teacher findTeacher(Long id) {
        Optional<Teacher> tch = teacherRepository.findById(id);
        if (tch.isEmpty()) {
            return null;
        } else {
            return tch.get();
        }
    }

    @Override
    public List<Student> getMyStudents(Long id) {
        return studentRepository.findStudentsByteachersId(id);
    }

    @Override
    public String addStudent(Long id, Long studentId) {
        Teacher tch = findTeacher(id);
        Student std = findStudent(studentId);
        tch.addStudent(std);
        teacherRepository.save(tch);
        studentRepository.save(std);
        return "Successfully added student";
    }

    @Override
    public String deleteStudent(Long id, Long studentId) {
        Teacher tch = findTeacher(id);
        Student std = findStudent(studentId);
        tch.removeStudent(std);
        teacherRepository.save(tch);
        studentRepository.save(std);
        return "Successfully deleted student";
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
    public List<Student> getStudents(Pagination pagination) {

        if (pagination.pageNo == 0) {

            if (pagination.sortKey.equals(""))
                return studentRepository.findAll();
            else
                return studentRepository.findAll(Sort.by(pagination.sortKey));
        } else {
            if (pagination.pageSize == 0) {

                if (pagination.sortKey.equals("")) {

                    Pageable paging = PageRequest.of(pagination.pageNo - 1, 5);
                    Page<Student> pagedStudent = studentRepository.findAll(paging);
                    return pagedStudent.toList();
                } else {

                    Pageable paging = PageRequest.of(pagination.pageNo - 1, 5, Sort.by(pagination.sortKey));
                    Page<Student> pagedStudent = studentRepository.findAll(paging);
                    return pagedStudent.toList();
                }
            } else {
                if (pagination.sortKey.equals("")) {

                    Pageable paging = PageRequest.of(pagination.pageNo - 1, pagination.pageSize);
                    Page<Student> pagedStudent = studentRepository.findAll(paging);
                    return pagedStudent.toList();
                } else {

                    Pageable paging = PageRequest.of(pagination.pageNo - 1, pagination.pageSize,
                            Sort.by(pagination.sortKey));
                    Page<Student> pagedStudent = studentRepository.findAll(paging);
                    return pagedStudent.toList();
                }
            }
        }

    }

    @Override
    public List<Student> searchStudent(String keyword, String name) {
        if (keyword.equals("firstname")) {

            return studentRepository.findByFirstName(name);
        } else
            return studentRepository.findByLastName(name);
    }

    @Override

    public Student findStudent(Long id) {
        Optional<Student> std = studentRepository.findById(id);
        if (std.isEmpty()) {
            return null;
        } else {
            return std.get();
        }
    }

    @Override
    public List<Teacher> getMyTeachers(Long id) {
        return teacherRepository.findTeachersBystudentsId(id);
    }

    @Override
    public String addTeacher(Long id, Long teacherId) {
        Teacher tch = findTeacher(teacherId);
        Student std = findStudent(id);
        tch.addStudent(std);
        teacherRepository.save(tch);
        studentRepository.save(std);
        return "Successfully added Teacher";
    }

    @Override
    public String deleteTeacher(Long id, Long teacherId) {
        Teacher tch = findTeacher(teacherId);
        Student std = findStudent(id);
        tch.removeStudent(std);
        teacherRepository.save(tch);
        studentRepository.save(std);
        return "Successfully deleted Teacher";
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