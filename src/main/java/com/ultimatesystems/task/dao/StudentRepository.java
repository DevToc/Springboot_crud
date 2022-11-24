package com.ultimatesystems.task.dao;

import com.ultimatesystems.task.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "students", path = "students")
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByFirstName(String firstName);

    Student findByLastName(String lastName);
}