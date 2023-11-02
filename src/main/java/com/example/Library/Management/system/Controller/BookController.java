package com.example.Library.Management.system.Controller;

import com.example.Library.Management.system.Entities.Book;
import com.example.Library.Management.system.Entities.Genre;
import com.example.Library.Management.system.Entities.LibraryCard;
import com.example.Library.Management.system.Service.BookService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;
    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody Book book, @RequestParam("authorid") Integer authorId){
        try{
            String result = bookService.addBook(book,authorId);
            return new ResponseEntity(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/get-book-by-genre")
    public List<String> getBookByGenre(@RequestParam("genre")Genre genre){
        return bookService.getBookByGenre(genre);
    }
}
