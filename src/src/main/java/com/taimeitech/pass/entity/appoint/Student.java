package com.taimeitech.pass.entity.appoint;

import java.sql.Timestamp;

/**
 * Created by devin on 2017/4/8.
 */
public class Student {
    private long studentId;
    private String studentName;
    private Timestamp student_create;
    private Timestamp student_modified;

    public Student() {
    }

    public Student(long studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Timestamp getStudent_create() {
        return student_create;
    }

    public void setStudent_create(Timestamp student_create) {
        this.student_create = student_create;
    }

    public Timestamp getStudent_modified() {
        return student_modified;
    }

    public void setStudent_modified(Timestamp student_modified) {
        this.student_modified = student_modified;
    }
}
