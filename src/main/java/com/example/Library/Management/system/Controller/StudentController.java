package com.example.Library.Management.system.Controller;

import com.example.Library.Management.system.Entities.Student;
import com.example.Library.Management.system.ResponseDTO.BasicDetailsStudentResponse;
import com.example.Library.Management.system.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody Student student){
        String result = studentService.addStudent(student);
//        Unidirectional mapping: only child table is having the reference object of parents
        return new ResponseEntity(result,HttpStatus.CREATED);

    }


    @GetMapping("/getBasicDetails")
    public ResponseEntity getBasicDetails(@RequestParam("id") Integer id){
        try {
            BasicDetailsStudentResponse resultObj = studentService.getBasicDetails(id);
            return new ResponseEntity(resultObj,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-students")
    public ResponseEntity fetchAllStudents(){
        List<String> studentNameList = studentService.fetchAllStudents();
        return new ResponseEntity(studentNameList, HttpStatus.OK);
    }
}
