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
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;
    //there are 5 types of strategies
    //identities: At table level, the primary key is unique at individual table level. For each table counting is different
    //auto: PK is unique at DB level
    //sequence:we can define PK as any AP sequence with common difference
    //table:it is used when we have dependent tables.
    //       whenever as student gets registered automatically student gets add into another table
    //       PK if the table is used as unique key of another table then table strategy is used
    //UUID:

    private String authorName;
    private  int age;
    private double rating;

    @JsonIgnore
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<Book> bookList = new ArrayList<>();

}
