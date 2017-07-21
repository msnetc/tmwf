package com.taimeitech.pass.entity.appoint;

import java.sql.Timestamp;

/**
 * 预约图书实体
 */
public class Appoint {

    private long bookId;// 图书ID
    private long studentId;// 学号
    private Timestamp appointTime;// 预约时间
    private Timestamp appointCreate;// 预约时间

    //one to one
    private Book book;// 图书实体

    public Appoint() {
    }

    public Appoint(long bookId, long studentId) {
        this.bookId = bookId;
        this.studentId = studentId;
    }

    public Appoint(long bookId, long studentId, Timestamp appointTime, Book book) {
        this.bookId = bookId;
        this.studentId = studentId;
        this.appointTime = appointTime;
        this.book = book;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public Timestamp getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(Timestamp appointTime) {
        this.appointTime = appointTime;
    }

    public Timestamp getAppointCreate() {
        return appointCreate;
    }

    public void setAppointCreate(Timestamp appointCreate) {
        this.appointCreate = appointCreate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
