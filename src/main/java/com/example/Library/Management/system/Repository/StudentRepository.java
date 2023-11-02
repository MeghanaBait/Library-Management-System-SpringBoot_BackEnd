package com.example.Library.Management.system.Repository;

import com.example.Library.Management.system.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    //JPARepository has two things-> first is table we really want to connect and the datatype of the primary key
    //we can you it for basic CRUD operations


}
