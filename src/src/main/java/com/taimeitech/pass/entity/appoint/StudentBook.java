package com.taimeitech.pass.entity.appoint;

import java.util.List;

/**
 * Created by devin on 2017/4/8.
 */
public class StudentBook {
    private long studentId;
    private String studentName;
    //一对多
    private List<Book> books;

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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "StudentBook{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", books=" + books +
                '}';
    }
}
