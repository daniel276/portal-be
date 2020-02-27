package com.studentportal.portal.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    private String department; // later change to Department class

    @NotBlank(message = "first name cannot be empty")
    private String firstName;

    @NotBlank(message = "last name cannot be empty")
    private String lastName;

    private String email;

    private float gpa;

    private Date dateofBirth;

    private String address;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "student_module",
//            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "module_id", referencedColumnName = "id"))
//    private ArrayList<Module> enrolledModules = new ArrayList<>();

    private Date createdAt;

    private Date updatedAt;

    public UserProfile() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public Date getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(Date dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public ArrayList<Module> getEnrolledModules() {
//        return enrolledModules;
//    }
//
//    public void setEnrolledModules(ArrayList<Module> enrolledModules) {
//        this.enrolledModules = enrolledModules;
//    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
