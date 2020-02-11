package com.studentportal.portal.domain;

import javax.persistence.*;

@Entity
@Table(name = "student_module")
public class StudentModule { //many-to-many moduleId - userAccount

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Module moduleId;

    private UserProfile userProfileId;

}
