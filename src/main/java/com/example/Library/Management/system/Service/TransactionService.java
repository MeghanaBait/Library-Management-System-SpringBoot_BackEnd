package com.example.Library.Management.system.Service;

import com.example.Library.Management.system.Entities.*;
import com.example.Library.Management.system.Exceptions.*;
import com.example.Library.Management.system.Repository.BookRepository;
import com.example.Library.Management.system.Repository.CardRepository;
import com.example.Library.Management.system.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    private static final Integer DAYS_ALLOWED_TO_KEEP_BOOK = 15;

    private static final Integer MAX_LIMIT_OF_BOOKS = 3;

    private static final Integer FINE_PER_DAY = 3;

    public String issueBook(Integer bookId, Integer cardId) throws Exception{
        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(TransactionStatus.PENDING);


        //validations
        //1.valid BookId
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(!optionalBook.isPresent()){
            throw new BookNotFoundException("Book Id is invalid");
        }

        Book book = optionalBook.get();

        //2.Book Availability
        if(!book.isAvailable()){
            throw new BookNotAvailableException("Book is unavailable");
        }

        //3.valid CardId
        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);
        if (!optionalLibraryCard.isPresent()){
            throw new CardNotFoundException("Card Id is invalid");
        }
        LibraryCard libraryCard = optionalLibraryCard.get();

        //4.valid card status
        if(!libraryCard.getCardStatus().equals(CardStatus.ACTIVE)){
            throw new InvalidCardStatusException("Card Status is not Active");
        }

        //5.maximum number of books issued :MAX_LIMIT = 3
        if(libraryCard.getNoOfBooksIssued() == MAX_LIMIT_OF_BOOKS){
            throw new MaximumBookIssueLimitReachedException(MAX_LIMIT_OF_BOOKS+" is maximum books that can be issued");
        }

        //setting transaction attributes
        transaction.setTransactionStatus(TransactionStatus.ISSUED);
        libraryCard.setNoOfBooksIssued(libraryCard.getNoOfBooksIssued()+1);
        book.setAvailable(false);//book is no longer available

        //FK Attributes
        transaction.setBook(book);
        transaction.setLibraryCard(libraryCard);

        book.getTransactionList().add(transaction);
        libraryCard.getTransactionList().add(transaction);

        //to avoid error of saving transaction, just save child entity instead of parents
        transactionRepository.save(transaction);

        return "The book with bookId "+bookId+" has been issued to card with "+cardId;
    }


    public String returnBook(Integer bookId, Integer cardId) throws Exception {
//        //bookId should be valid
//        //card Id valid
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(!optionalBook.isPresent()){
            throw new BookNotFoundException("Invalid book ID");
        }
//
        Book book = optionalBook.get();

        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);
        if (optionalLibraryCard.isPresent()){
            throw new CardNotFoundException("Invalid Card ID");
        }
        LibraryCard card = optionalLibraryCard.get();

        Transaction transaction = transactionRepository.findTransactionByBookAndLibraryCardAndTransactionStatus(book, card, TransactionStatus.ISSUED);
//issue date
        Date issueDate = transaction.getCreatedOn();

        //predefined method for calculating days
        long milliSeconds = Math.abs(System.currentTimeMillis()-issueDate.getTime());
        long days = TimeUnit.DAYS.convert(milliSeconds,TimeUnit.MILLISECONDS);
//fine
        int fineAmount = 0;

        if(days > DAYS_ALLOWED_TO_KEEP_BOOK){
            fineAmount = (int) ((days - DAYS_ALLOWED_TO_KEEP_BOOK)*FINE_PER_DAY);
        }

    //creating new transaction
        Transaction newTransaction = new Transaction();

        newTransaction.setTransactionStatus(TransactionStatus.COMPLETED);
    //return date
        newTransaction.setReturnDate(new Date());
        newTransaction.setFine(fineAmount);

        //Setting foreign key
        newTransaction.setBook(book);
        newTransaction.setLibraryCard(card);

        book.setAvailable(true);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()-1);

        book.getTransactionList().add(newTransaction);
        card.getTransactionList().add(newTransaction);

        transactionRepository.save(newTransaction);

        return "Book has been returned";
    }

    public Long getTotalRevenueCollectedByLibrary() {
        List<Transaction> transactionList = transactionRepository.findAll();
        Long revenue = 0L;
        for(Transaction transaction : transactionList){
            if(transaction.getFine()==null){
                continue;
            }
            revenue += transaction.getFine();
        }
        return revenue;
    }
}
