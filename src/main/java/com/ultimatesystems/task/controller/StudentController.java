package com.ultimatesystems.task.controller;

import com.ultimatesystems.task.entity.Pagination;
import com.ultimatesystems.task.entity.Student;
import com.ultimatesystems.task.entity.Teacher;
import com.ultimatesystems.task.service.PersonService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/students")

public class StudentController {

    private PersonService personService;

    public StudentController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "all")
    public List<Student> getElements(@RequestBody Pagination pagination) {

        return personService.getStudents(pagination);
    }

    @PostMapping(value = "search/{keyword}/{name}")
    public List<Student> searchElement(@PathVariable("keyword") String keyword, @PathVariable("name") String name) {
        return personService.searchStudent(keyword, name);
    }

    @PostMapping(value = "{id}/teachers")
    public List<Teacher> addTeacher(@PathVariable("id") Long id) {

        return personService.getMyTeachers(id);
    }

    @PostMapping(value = "{id}/addTeacher/{teacherId}")
    public String addTeacher(@PathVariable("id") Long id,
            @PathVariable("teacherId") Long teacherId) {

        return personService.addTeacher(id, teacherId);
    }

    @PostMapping(value = "{id}/deleteTeacher/{teacherId}")
    public String deleteTeacher(@PathVariable("id") Long id,
            @PathVariable("teacherId") Long teacherId) {

        return personService.deleteTeacher(id, teacherId);
    }

    @PostMapping(value = "add")
    public String createElement(@RequestBody Student student) {

        return personService.createStudent(student);
    }

    @DeleteMapping(value = "delete/{id}")
    public String deleteElement(@PathVariable("id") Long id) {
        return personService.deleteStudent(id);
    }

    @PutMapping("/update/{id}")
    public String updateElement(@PathVariable("id") Long id, @RequestBody Student student) {
        return personService.updateStudent(id, student);
    }

}