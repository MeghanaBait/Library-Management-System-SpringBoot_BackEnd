package com.example.Library.Management.system.Service;

import com.example.Library.Management.system.Entities.Student;
import com.example.Library.Management.system.Repository.StudentRepository;
import com.example.Library.Management.system.ResponseObject.BasicDetailsStudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public String addStudent(Student student) {
        studentRepository.save(student);
        return "Student added to DB successfully";
    }

    public BasicDetailsStudentResponse getBasicDetails(Integer id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        Student student = null;
        if(optionalStudent.isPresent()){
            student = optionalStudent.get();
        }
        BasicDetailsStudentResponse result = new BasicDetailsStudentResponse();
        result.setAge(student.getAge());
        result.setName(student.getStudentName());
        result.setMobNo(student.getMobNo());

        return result;
    }

    public List<String> fetchAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<String> studentNameList = new ArrayList<>();

        for(Student student : students){
            studentNameList.add(student.getStudentName());
        }
        return studentNameList;
    }
}
