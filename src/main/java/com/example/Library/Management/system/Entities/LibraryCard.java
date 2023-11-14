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
@Table(name = "library_card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private String NameOnCard;

    private Integer NoOfBooksIssued;

    @OneToOne
    @JoinColumn
    //@JsonIgnore
    private Student studentVariable;//acts as foreign key for library card table
    //this variable is to be put in mapped by attribute in the parent class

    //@JsonIgnore
    @OneToMany(mappedBy = "libraryCard", cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();
}
