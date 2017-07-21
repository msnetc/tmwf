package com.taimeitech.pass.api.appoint;

import com.taimeitech.framework.common.dto.ActionResult;
import com.taimeitech.pass.service.appoint.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//@RequestMapping("/moduleName")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/students/{studentId}")
    private ActionResult getStudent(@PathVariable("studentId") long studentId) {
        return studentService.getStudent(studentId);
    }

    @GetMapping(value = "/user/{studentId}")
    private String getUser(@PathVariable("studentId") long studentId) {
        return "hello" + studentId;
    }

}
