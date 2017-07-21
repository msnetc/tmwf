package com.taimeitech.pass.entity.appoint;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;

/**
 * 图书实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {
    private long bookId;        // 图书ID
    private String bookName;    // 图书名称
    private int bookNumber;     // 馆藏数量
    private Timestamp bookCreate;
    private Timestamp bookModified;

    public Book() {
    }

    public Book(long bookId, String bookName, int bookNumber) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookNumber = bookNumber;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }

    public Timestamp getBookCreate() {
        return bookCreate;
    }

    public void setBookCreate(Timestamp bookCreate) {
        this.bookCreate = bookCreate;
    }

    public Timestamp getBookModified() {
        return bookModified;
    }

    public void setBookModified(Timestamp bookModified) {
        this.bookModified = bookModified;
    }
}
