package com.learn.learn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.learn.Model.Marks;

@Repository
public interface MarksRepo extends JpaRepository<Marks,Long>{
    
}
