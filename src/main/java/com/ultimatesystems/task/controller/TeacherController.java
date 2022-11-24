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
@RequestMapping("/api/teachers")
public class TeacherController {

    private PersonService personService;

    public TeacherController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "all")
    public List<Teacher> getElements(@RequestBody Pagination pagination) {

        return personService.getTeachers(pagination);
    }

    @PostMapping(value = "search/{keyword}/{name}")
    public List<Teacher> searchElement(@PathVariable("keyword") String keyword, @PathVariable("name") String name) {
        return personService.searchTeacher(keyword, name);
    }

    @PostMapping(value = "{id}/students")
    public List<Student> addStudent(@PathVariable("id") Long id) {

        return personService.getMyStudents(id);
    }

    @PostMapping(value = "{id}/addStudent/{studentId}")
    public String addStudent(@PathVariable("id") Long id,
            @PathVariable("studentId") Long studentId) {

        return personService.addStudent(id, studentId);
    }

    @PostMapping(value = "{id}/deleteStudent/{studentId}")
    public String deleeStudent(@PathVariable("id") Long id,
            @PathVariable("studentId") Long studentId) {

        return personService.addStudent(id, studentId);
    }

    @PostMapping(value = "add")
    public String createElement(@RequestBody Teacher teacher) {

        return personService.createTeacher(teacher);
    }

    @DeleteMapping(value = "delete/{id}")
    public String deleteElement(@PathVariable("id") Long id) {
        return personService.deleteTeacher(id);
    }

    @PutMapping("/update/{id}")
    public String updateElement(@PathVariable("id") Long id, @RequestBody Teacher teacher) {
        return personService.updateTeacher(id, teacher);
    }

}