package com.example.Library.Management.system.Controller;

import com.example.Library.Management.system.Entities.Book;
import com.example.Library.Management.system.Entities.Genre;
import com.example.Library.Management.system.Entities.LibraryCard;
import com.example.Library.Management.system.Service.BookService;
import lombok.Data;
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
    public ResponseEntity getBookByGenre(@RequestParam("genre")Genre genre){
        List<String> books = bookService.getBookByGenre(genre);
        return new ResponseEntity(books,HttpStatus.OK);
    }


    @GetMapping("/get-most-issued-book")
    public ResponseEntity getMostIssuedBook(){
        try {
            List<String> books = bookService.getMostIssuedBook();
            return new ResponseEntity(books, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity deleteBook(@PathVariable("bookId") Integer bookId){
        try{
            String result = bookService.deleteBook(bookId);
            return new ResponseEntity(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
