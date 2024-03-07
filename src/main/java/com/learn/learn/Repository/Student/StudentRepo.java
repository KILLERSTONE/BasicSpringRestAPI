package com.learn.learn.Repository.Student;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learn.learn.Model.Student.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student,String>{

    @Modifying
    @Query("UPDATE Student s SET s.name = :newName WHERE s.regNo = :regNo")
    String updateStudentByRegNo(@Param("newName") String newName, @Param("regNo") String regNo);
    
    

    List<Student> findByRegNoIn(List<String> regNos);

}
