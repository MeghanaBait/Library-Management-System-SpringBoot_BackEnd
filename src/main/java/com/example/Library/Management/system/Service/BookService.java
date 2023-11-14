package com.example.Library.Management.system.Service;

import com.example.Library.Management.system.Entities.*;
import com.example.Library.Management.system.Exceptions.AuthorNotFoundException;
import com.example.Library.Management.system.Exceptions.BookNotFoundException;
import com.example.Library.Management.system.Exceptions.NoBooksIssued;
import com.example.Library.Management.system.Repository.AuthorRepository;
import com.example.Library.Management.system.Repository.BookRepository;
import com.example.Library.Management.system.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public String addBook(Book book,Integer authorId) throws AuthorNotFoundException {
      //check whether author with authorID is present or not
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isEmpty()){
            throw new AuthorNotFoundException("Author id entered is invalid");
        }
        Author author = optionalAuthor.get();
        book.setAvailable(true);
        book.setAuthor(author);
        //its bidirectional mapping , author should also have the information of book entity;
        //set author rating
        Double rating = ((author.getRating() *author.getBookList().size())+ book.getRating())/(author.getBookList().size()+1);

        author.getBookList().add(book);

        authorRepository.save(author);
        //only the author entity should be saved because of cascading effect, book entity will get autosaved
        return "Book has been added to DB";
    }


    public List<String> getBookByGenre(Genre genre){
        List<Book> bookList = bookRepository.findBookByGenre(genre);
        List<String>bookNames = new ArrayList<>();

        for(Book book: bookList){
            bookNames.add(book.getBookName());
        }

        return bookNames;
    }

    public String deleteBook(Integer bookId) throws Exception {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(!optionalBook.isPresent()){
            throw new BookNotFoundException("Book Id is invalid");
        }

        bookRepository.deleteById(bookId);
        return "Book has been deleted successfully";
    }

    public List<String> getMostIssuedBook() throws NoBooksIssued {

        List<String> mostIssuedBooks = new ArrayList<>();

        List<Transaction> transactionList = transactionRepository.findAll();

        Map<String, Integer> issuedBookCountMap = new HashMap<>();

        for(Transaction transaction : transactionList){
            if(transaction.getTransactionStatus().equals(TransactionStatus.ISSUED)){
                String bookName = transaction.getBook().getBookName();
                issuedBookCountMap.put(bookName,issuedBookCountMap.getOrDefault(bookName,0)+1);
            }
        }

        int maxIssue = 0;
        for(Integer value :issuedBookCountMap.values()){
            maxIssue = Math.max(maxIssue,value);
        }

        for(String bookName : issuedBookCountMap.keySet()){
            if(issuedBookCountMap.get(bookName) == maxIssue){
                mostIssuedBooks.add(bookName);
            }
        }

        if(mostIssuedBooks.isEmpty()){
            throw new NoBooksIssued("No book issued yet");
        }
        return mostIssuedBooks;
    }
}
