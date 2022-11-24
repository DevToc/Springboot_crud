package com.ultimatesystems.task.controller;

import com.ultimatesystems.task.entity.Student;
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
    public List<Student> getElements() {

        return personService.getStudents();
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