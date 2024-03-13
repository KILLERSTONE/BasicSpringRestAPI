package com.learn.learn.Service.Subject;

import java.util.List;

import com.learn.learn.Model.Subject;

public interface SubjectServices {
    

    void saveSubject(Subject sub);
    Subject getSubjectById(Long id);
    List<Subject> getAllSubjects();

    void deleteSubjectById(Long id);

    void assignTeacherToSubject(Long subId,Long teachId);

    void assignStudentToSubject(Long subId,String stdId);


    void deleteSubjectByTeacherId(Long subId,Long teacherId);

    void deleteAllSubjectByTeacherId(Long teacherId);

    void deleteStudentfromSubject(Long subID,String id);

    void deleteStudentFromAllSubject(String id);
}
