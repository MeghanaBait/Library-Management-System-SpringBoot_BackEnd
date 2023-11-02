package com.example.Library.Management.system.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//Jakarta is a part of JPA library helps to create entity related annotations
@Entity//this is a structure that will be reflected in database
@Table(name = "Student")//this class denoting the schema of the tables...every class will describe the particular table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;//this will behave as a primary key
    private String studentName;
    private int age;
    @Column(name = "Contact No.", unique = true, nullable = false)
    private String mobNo;
    private String emailId;
    private String bloodGroup;

    @JsonIgnore
    @OneToOne(mappedBy = "studentVariable", cascade = CascadeType.ALL)
    private LibraryCard libraryCard;
    //in mappedby write variable name of foreign key column in child class
    //cascade all the type of methods of parent class to child class also.
}
