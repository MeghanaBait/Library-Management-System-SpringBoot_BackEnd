package com.example.Library.Management.system.Service;

import com.example.Library.Management.system.Entities.Author;
import com.example.Library.Management.system.Entities.Book;
import com.example.Library.Management.system.Entities.Genre;
import com.example.Library.Management.system.Exceptions.AuthorNotFoundException;
import com.example.Library.Management.system.Repository.AuthorRepository;
import com.example.Library.Management.system.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public String addBook(Book book,Integer authorId) throws AuthorNotFoundException {
      //check whether author with authorID is present or not
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (!optionalAuthor.isPresent()){
            throw new AuthorNotFoundException("Author id entered is invalid");
        }
        Author author = optionalAuthor.get();
        book.setAvailable(true);
        book.setAuthor(author);
        //its bidirectional mapping , author should also have the information of book entity;
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

}
