package com.example.Library.Management.system.ResponseObject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasicDetailsStudentResponse {
    private String name;
    private int age;
    private String mobNo;
}
