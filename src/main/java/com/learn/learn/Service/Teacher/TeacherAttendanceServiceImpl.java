package com.learn.learn.Service.Teacher;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.learn.Exception.UserNotFoundException;
import com.learn.learn.Model.Subject;
import com.learn.learn.Model.Teacher.Teacher;
import com.learn.learn.Model.Teacher.TeacherAttendance;
import com.learn.learn.Repository.SubjectRepo;
import com.learn.learn.Repository.Teacher.TeacherAttendanceRepo;
import com.learn.learn.Repository.Teacher.TeacherRepo;

@Service
public class TeacherAttendanceServiceImpl implements TeacherAttendanceService {

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private TeacherAttendanceRepo teacherAttendanceRepo;

    @Autowired
    private SubjectRepo subjectRepo;

    @Override
    public void setAttendance(Long teacherId, TeacherAttendance attendance) {
        // TODO Auto-generated method stub

        Teacher teach = teacherRepo.findById(teacherId).orElse(null);

        if (teach == null)
            throw new UserNotFoundException("No teacher of this id found");

        TeacherAttendance attd = teacherAttendanceRepo.findByTeacherAndDate(teach, attendance.getDate());

        if (attd == null) {
            attd = new TeacherAttendance();
            attd.setTeacher(teach);
            attd.setDate(attendance.getDate());
            attd.setStatus(attendance.getStatus());
        } else {
            attd.setStatus(attendance.getStatus());
        }

        teacherAttendanceRepo.save(attd);
    }

    @Override
    public List<TeacherAttendance> getAttendance(Long teacherId) {
        // TODO Auto-generated method stub

        Teacher teach = teacherRepo.findById(teacherId).orElse(null);

        if (teach == null)
            throw new UserNotFoundException("No teacher of this id exists");
        return teacherAttendanceRepo.findAllByTeacher(teach);
    }

    @Override
    public List<TeacherAttendance> getAttendanceOnDate(LocalDate date) {
        // TODO Auto-generated method stub

        List<TeacherAttendance> attendances = teacherAttendanceRepo.findAllByDate(date);
        return attendances;
    }

    @Override
    public void markAttendanceForTeacherOnDate(Long teacherId, LocalDate date, String status) {

        Teacher teach = teacherRepo.findById(teacherId).orElse(null);
        if (teach == null)
            throw new UserNotFoundException("No teacher of this id exists");

        TeacherAttendance attd = teacherAttendanceRepo.findByTeacherAndDate(teach, date);

        if (attd == null) {
            attd = new TeacherAttendance();
            attd.setDate(date);
            attd.setStatus(status);
            attd.setTeacher(teach);
        } else {
            attd.setStatus(status);
        }

        teacherAttendanceRepo.save(attd);

    }

    @Override
    public void deleteAttendanceByTeacherId(Long teacherId) {
        List<TeacherAttendance> attd = teacherAttendanceRepo.findAllByTeacherId(teacherId);

        if (attd != null){
            for(TeacherAttendance a:attd){
                teacherAttendanceRepo.delete(a);
            }
        }
    }

}
