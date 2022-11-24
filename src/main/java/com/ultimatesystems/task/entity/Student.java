package com.ultimatesystems.task.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student extends Person {

    @Column(name = "degree_programme")
    @Setter
    @Getter
    private String degreeProgramme;

    @ManyToMany(mappedBy = "students")
    private Set<Teacher> teachers = new HashSet<>();
}