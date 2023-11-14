package com.example.Library.Management.system.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    private String bookName;
    private int noOfPages;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    private int price;
    private double rating;
    private Integer publishedYear;
    private boolean isAvailable;


//  unidirectional mapping
    @ManyToOne
    @JoinColumn
   // @JsonIgnore
    private Author author;
    // biderctional mapping:
//    in parent table also child information is saved.
//    whatever CRUD operation applied on the child,
//    the same CRUD operation will be applied o parent entity. in this way consistency of the table is maintained
// it is written in parent class and child class will be automatically connected


    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Transaction> transactionList =new ArrayList<>();

}
