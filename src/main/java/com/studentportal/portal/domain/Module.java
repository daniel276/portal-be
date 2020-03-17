package com.studentportal.portal.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "module")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotBlank(message = "module code cannot be empty")
    private String code;

    @NotBlank(message = "module name cannot be empty")
    private String name;

    private float credit;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @OneToMany(mappedBy = "module")
    private Set<ModuleEnrollment> moduleEnrollments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "module", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private List<Class> classList = new ArrayList<>();

//    @ManyToMany(mappedBy = "enrolledModules")
//    private Set<UserProfile> students;

//    @ManyToMany(mappedBy = "")

    public Module() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<ModuleEnrollment> getModuleEnrollments() {
        return moduleEnrollments;
    }

    public void setModuleEnrollments(Set<ModuleEnrollment> moduleEnrollments) {
        this.moduleEnrollments = moduleEnrollments;
    }

    public List<Class> getClassList() {
        return classList;
    }

    public void setClassList(List<Class> classList) {
        this.classList = classList;
    }

//    public Set<UserProfile> getStudents() {
//        return students;
//    }
//
//    public void setStudents(Set<UserProfile> students) {
//        this.students = students;
//    }
}
