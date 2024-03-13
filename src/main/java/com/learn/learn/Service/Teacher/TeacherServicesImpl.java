package com.learn.learn.Service.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.learn.Model.Subject;
import com.learn.learn.Model.Teacher.Teacher;
import com.learn.learn.Repository.SubjectRepo;
import com.learn.learn.Repository.Teacher.TeacherRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TeacherServicesImpl implements TeacherServices{

    @Autowired
    private TeacherRepo teacherRepo;
    
    @Autowired
    private SubjectRepo subRepo;

    @Override
    public Long saveTeacher(Teacher teacher) {
        // TODO Auto-generated method stub

        List<Subject> savedSubjects=new ArrayList<>();
        for(Subject sub:savedSubjects){
            if(sub.getId()==null){
                savedSubjects.add(subRepo.save(sub));
            }
            else{
                savedSubjects.add(sub);
            }
        }

        teacher.setSubjects(savedSubjects);

        return teacherRepo.save(teacher).getId();
    }

    @Override
    public Teacher getTeacherById(Long id) {

        Optional<Teacher> teach=teacherRepo.findById(id);

        return teach.orElse(null);
    }

    @Override
    public List<Teacher> getAllTeachers() {

        List<Teacher> teachers=teacherRepo.findAll();

        return teachers;
    }

    @Override
    public void deleteTeacherById(Long id) {

        Teacher teach=teacherRepo.findById(id).orElse(null);
        teacherRepo.delete(teach);
    }
    
    @Override
    public void deleteTeacherByName(String name){
        Teacher teach=teacherRepo.findByName(name);

        teacherRepo.delete(teach);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        // TODO Auto-generated method stub
        teacherRepo.save(teacher);
    }
}
