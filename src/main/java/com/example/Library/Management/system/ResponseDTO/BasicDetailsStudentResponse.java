package com.example.Library.Management.system.ResponseDTO;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasicDetailsStudentResponse {
    private String name;
    private int age;
    private String mobNo;
    private String email;
    private String bloodGroup;
    private Integer cardNo;
}
