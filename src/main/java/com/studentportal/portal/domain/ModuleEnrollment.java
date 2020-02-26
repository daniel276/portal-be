package com.studentportal.portal.domain;

import javax.persistence.*;

@Entity
public class ModuleEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private UserProfile student;

    @ManyToOne
    @JoinColumn(name = "module_id")
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

    public UserProfile getStudent() {
        return student;
    }

    public void setStudent(UserProfile student) {
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
