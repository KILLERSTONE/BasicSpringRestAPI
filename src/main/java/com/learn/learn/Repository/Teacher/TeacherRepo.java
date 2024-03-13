package com.learn.learn.Repository.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.learn.Model.Teacher.Teacher;
import java.util.List;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {

    List<Teacher> findByIdIn(List<Long> ids); // Get all teachers based on list of ids

    List<Teacher> findByDesignation(String designation);

    Teacher findByName(String name);

}
