package com.example.Library.Management.system.Service;

import com.example.Library.Management.system.Entities.Author;
import com.example.Library.Management.system.Entities.Book;
import com.example.Library.Management.system.Exceptions.AuthorNotFoundException;
import com.example.Library.Management.system.Repository.AuthorRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public String addAuthor(Author author){
        authorRepository.save(author);
        return "Author added successfully";
    }

    public List<String> getAllAuthorNames() {
        List<Author> authors = authorRepository.findAll();

        List<String> authorNames = new ArrayList<>();
        for (Author author: authors){
            authorNames.add(author.getAuthorName());
        }
        return authorNames;
    }


    public Author getAuthorById(Integer id) throws Exception {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        //author object can have value or there will be no value so the return type is optional
        if(!optionalAuthor.isPresent()){
            //throw an error
            //top controller layer: try-catch
            //bottom layer: use throws keyword to throw error to upper layer
            throw new Exception("The Id entered is invalid");
        }
        Author author = optionalAuthor.get();
        return author;
    }

    public List<String> getBookNamesList(Integer authorId){
        List<String> bookNames =  new ArrayList<>();
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        Author author = optionalAuthor.get();

        List<Book> bookList = author.getBookList();
        for(Book book: bookList){
            bookNames.add(book.getBookName());
        }
        return bookNames;
    }

    public Long getAverageRatingOfAllBookWrittenByAuthor(Integer authorId) throws Exception {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        if(!optionalAuthor.isPresent()){
            throw new AuthorNotFoundException("Invalid author Id");
        }

        Author author = optionalAuthor.get();

        List<Book> bookList = author.getBookList();
        long totalRating = 0;
        for(Book book : bookList){
            totalRating += book.getRating();
        }

        long avgRating = totalRating / bookList.size();
        return avgRating;
    }
}
