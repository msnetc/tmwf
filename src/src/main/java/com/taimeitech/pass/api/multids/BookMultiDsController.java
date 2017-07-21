package com.taimeitech.pass.api.multids;

import com.taimeitech.pass.entity.appoint.Book;
import com.taimeitech.pass.service.multids.BookMultiDsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhengjun.zhang on 2017/6/7.
 */
@RestController
@RequestMapping("multids")
public class BookMultiDsController {
    @Autowired
    private BookMultiDsService bookService;

    @GetMapping(value = "/list1")
    public List<Book> listBook1() {
        return bookService.listBook1();
    }

    @GetMapping(value = "/list2")
    public List<Book> listBook2() {
        return bookService.listBook2();
    }

}
