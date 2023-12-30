package com.learn.learn.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learn.learn.Model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student,String>{

    @Modifying
    @Query("update Student s set s.name=:newname where s.reg_no=:reg_no")
    String updateStudentByRegNo(@Param("newname")String newname,@Param("reg_no") String reg_no);
    
}
