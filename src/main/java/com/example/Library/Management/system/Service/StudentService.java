package com.example.Library.Management.system.Service;

import com.example.Library.Management.system.Entities.Student;
import com.example.Library.Management.system.Exceptions.StudentNotFound;
import com.example.Library.Management.system.Repository.StudentRepository;
import com.example.Library.Management.system.ResponseDTO.BasicDetailsStudentResponse;
import com.example.Library.Management.system.Transformer.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public BasicDetailsStudentResponse getBasicDetails(Integer id) throws StudentNotFound {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(!optionalStudent.isPresent()){
            throw new StudentNotFound("Invalid Student ID");
        }
        Student student = optionalStudent.get();

        BasicDetailsStudentResponse basicDetailsStudent = StudentTransformer.convertEntityToResponse(student);
        return basicDetailsStudent;
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
