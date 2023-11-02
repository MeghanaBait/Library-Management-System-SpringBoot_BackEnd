package com.example.Library.Management.system.Controller;

import com.example.Library.Management.system.Entities.Book;
import com.example.Library.Management.system.Entities.Student;
import com.example.Library.Management.system.ResponseObject.BasicDetailsStudentResponse;
import com.example.Library.Management.system.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
//        Unidirectional mapping: only child table is having the reference object of parents

    }


    @GetMapping("/getBasicDetails")
    public BasicDetailsStudentResponse getBasicDetails(@RequestParam("id") Integer id){
        BasicDetailsStudentResponse resultObj = studentService.getBasicDetails(id);
        return resultObj;
    }


}
