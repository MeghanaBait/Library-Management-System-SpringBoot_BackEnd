package com.example.Library.Management.system.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;
    private Date returnDate;
    private Integer fine;

    @CreationTimestamp
    private Date createdOn;//issue date

    @UpdateTimestamp
    private Date lastModifiedOn;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private LibraryCard libraryCard;


}
