package com.evergreenClasses.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dob;
    private String email;
    private String mobileNo;
    private String batchName;
    private String batchYear;
    private String studentClass;
    private String fatherName;
    private String fatherMobileNo;

    private String village;
    private String post;
    private String district;
    private String state;
    private String pincode;

    //auto generated rollno
    private String rollNo;

    @ManyToOne
    @JoinColumn(name = "batchId")
    private Batch batch; 

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval =true)
    private List<Result> results;


}
