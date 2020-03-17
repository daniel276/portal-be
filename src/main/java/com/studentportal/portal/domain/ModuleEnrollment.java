package com.studentportal.portal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "module_enrollment")
public class ModuleEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private UserLogin student;

    @ManyToOne
    @JoinColumn(name = "module_id")
    @JsonIgnore
    private Module module;

    private int grade;

    public ModuleEnrollment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserLogin getStudent() {
        return student;
    }

    public void setStudent(UserLogin student) {
        this.student = student;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
