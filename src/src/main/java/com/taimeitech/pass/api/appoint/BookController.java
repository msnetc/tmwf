package com.taimeitech.pass.api.appoint;

import com.taimeitech.framework.common.dto.ActionResult;
import com.taimeitech.framework.common.dto.ErrorInfo;
import com.taimeitech.framework.util.StringUtil;
import com.taimeitech.pass.service.appoint.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

// url:/模块/资源/{id}/细分
@RestController
//@RequestMapping("/moduleName")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/books")
    private ActionResult listBook() {
        return bookService.listBook();
    }

    @GetMapping(value = "/books/{bookId}")
    private ActionResult getBookById(@PathVariable("bookId") Long bookId) {
        return bookService.getBookById(bookId);
    }

    @PutMapping(value = "/books/{bookId}")
    private ActionResult updateBook(@PathVariable("bookId") Long bookId, HttpServletRequest request) {
        String studentName = request.getParameter("studentName");
        if (StringUtil.isParamsEmpty(studentName)) {
            return new ActionResult(false, Arrays.asList(new ErrorInfo(-2, "参数缺失")), null);
        }

        //调用业务Service，此处代码省略

        return new ActionResult(true, null, null);
    }

}
