package com.ultimatesystems.task.dao;

import com.ultimatesystems.task.entity.Teacher;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "teachers", path = "teachers")
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findByFirstName(String firstName);

    List<Teacher> findByLastName(String lastName);

    List<Teacher> findTeachersBystudentsId(Long studentId);
}