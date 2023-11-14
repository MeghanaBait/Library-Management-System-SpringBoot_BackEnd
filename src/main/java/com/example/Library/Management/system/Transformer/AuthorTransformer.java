package com.example.Library.Management.system.Transformer;

import com.example.Library.Management.system.Entities.Author;
import com.example.Library.Management.system.Entities.Book;
import com.example.Library.Management.system.ResponseDTO.AuthorDetailsResponse;

import java.util.ArrayList;
import java.util.List;

public class AuthorTransformer {
    public static AuthorDetailsResponse convertEntityToResponse(Author author){
        List<Book> bookList =author.getBookList();
        List<String> bookNamesList = new ArrayList<>();
        for(Book b : bookList){
            bookNamesList.add(b.getBookName());
        }
        AuthorDetailsResponse authorDetailsResponse = AuthorDetailsResponse.builder()
                .authorId(author.getAuthorId())
                .authorName(author.getAuthorName())
                .age(author.getAge())
                .rating(author.getRating())
                .bookList(bookNamesList).build();

        return authorDetailsResponse;
    }
}
