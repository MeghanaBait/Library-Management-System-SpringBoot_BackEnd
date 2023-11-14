package com.example.Library.Management.system.ResponseDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder

public class AuthorDetailsResponse {
    private Integer authorId;
    private String authorName;
    private Integer age;
    private double rating;
    private List<String> bookList;
}
