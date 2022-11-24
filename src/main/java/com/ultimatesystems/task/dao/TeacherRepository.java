package com.ultimatesystems.task.dao;

import com.ultimatesystems.task.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "teachers", path = "teachers")
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByFirstName(String firstName);

    Teacher findByLastName(String lastName);
}