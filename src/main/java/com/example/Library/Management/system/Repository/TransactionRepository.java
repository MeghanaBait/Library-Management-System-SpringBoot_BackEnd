package com.example.Library.Management.system.Repository;

import com.example.Library.Management.system.Entities.Book;
import com.example.Library.Management.system.Entities.LibraryCard;
import com.example.Library.Management.system.Entities.Transaction;
import com.example.Library.Management.system.Entities.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
   Transaction findTransactionByBookAndLibraryCardAndTransactionStatus(Book book, LibraryCard libraryCard, TransactionStatus transactionStatus);
}
