package com.example.Library.Management.system.Transformer;

import com.example.Library.Management.system.Entities.Student;
import com.example.Library.Management.system.ResponseDTO.BasicDetailsStudentResponse;

public class StudentTransformer {
    public static BasicDetailsStudentResponse convertEntityToResponse(Student student){
        BasicDetailsStudentResponse basicDetailsStudentResponse = BasicDetailsStudentResponse.builder()
                .name(student.getStudentName())
                .age(student.getAge())
                .mobNo(student.getMobNo())
                .email(student.getEmailId())
                .bloodGroup(student.getBloodGroup())
                .cardNo(student.getLibraryCard().getCardId())
                .build();

        return basicDetailsStudentResponse;
    }
}
