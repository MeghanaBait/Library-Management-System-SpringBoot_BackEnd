package com.example.Library.Management.system.Service;

import com.example.Library.Management.system.Entities.Author;
import com.example.Library.Management.system.Entities.Book;
import com.example.Library.Management.system.Exceptions.AuthorNotFoundException;
import com.example.Library.Management.system.Repository.AuthorRepository;
import com.example.Library.Management.system.ResponseDTO.AuthorDetailsResponse;
import com.example.Library.Management.system.Transformer.AuthorTransformer;
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


    public AuthorDetailsResponse getAuthorById(Integer id) throws Exception {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if(optionalAuthor.isEmpty()){
            throw new Exception("The Id entered is invalid");
        }
        Author author = optionalAuthor.get();

        return AuthorTransformer.convertEntityToResponse(author);
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

    public double getAverageRatingOfAllBookWrittenByAuthor(Integer authorId) throws Exception {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        if(!optionalAuthor.isPresent()){
            throw new AuthorNotFoundException("Invalid author Id");
        }

        Author author = optionalAuthor.get();

        List<Book> bookList = author.getBookList();
        double totalRating = 0;
        for(Book book : bookList){
            totalRating += book.getRating();
        }
        //#not getting long value
        double avgRating = totalRating / bookList.size();
        return avgRating;
    }
}
