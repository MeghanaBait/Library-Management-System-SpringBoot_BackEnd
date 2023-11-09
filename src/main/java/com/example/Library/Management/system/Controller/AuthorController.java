package com.example.Library.Management.system.Controller;

import com.example.Library.Management.system.Entities.Author;
import com.example.Library.Management.system.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestBody Author author){
        return new ResponseEntity<>(authorService.addAuthor(author), HttpStatus.CREATED);
    }

    @GetMapping("/find-all-author-name")
    public ResponseEntity<List<String>>getAllAuthorNames(){

        return new ResponseEntity<>(authorService.getAllAuthorNames(),HttpStatus.OK);
    }

    @GetMapping("get-author/{id}")
    public ResponseEntity getAuthorById(@PathVariable("id") Integer id){
        try {
            return new ResponseEntity<>(authorService.getAuthorById(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/get-book-names-list")
    public ResponseEntity getBookNameList(@RequestParam("authorid") Integer authorId){
        List<String> bookNames = authorService.getBookNamesList(authorId);
        return new ResponseEntity(bookNames,HttpStatus.OK);
    }

    @GetMapping("/avg-rating/{authorId}")
    public ResponseEntity getAverageRatingOfAllBookWrittenByAuthor(@PathVariable("authorId") Integer authorId){
        try {
            Long rating = authorService.getAverageRatingOfAllBookWrittenByAuthor(authorId);
            return new ResponseEntity(rating, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
