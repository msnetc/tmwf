package com.taimeitech.pass.service.appoint.impl;

import com.taimeitech.framework.common.dto.ActionResult;
import com.taimeitech.framework.common.dto.ErrorInfo;
import com.taimeitech.pass.dao.appoint.BookDao;
import com.taimeitech.pass.entity.appoint.Book;
import com.taimeitech.pass.service.appoint.BookService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public ActionResult listBook() {
        List<Book> list = bookDao.listBook(0, 1000);
        if (CollectionUtils.isEmpty(list)) {
            return new ActionResult(false, Arrays.asList(new ErrorInfo(-1, "数据为空")), null);
        } else {
            return new ActionResult(true, null, list);
        }
    }

    @Override
    public ActionResult getBookById(long bookId) {
        Book book = bookDao.getBookById(bookId);

        if (book != null) {
            return new ActionResult<>(true, null, book);
        } else {
            return new ActionResult<Book>(false, Arrays.asList(new ErrorInfo(-1, "数据为空")), null);
        }
    }

}
