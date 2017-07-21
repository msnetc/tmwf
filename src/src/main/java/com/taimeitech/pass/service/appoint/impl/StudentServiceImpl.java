package com.taimeitech.pass.service.appoint.impl;

import com.taimeitech.framework.common.dto.ActionResult;
import com.taimeitech.framework.common.dto.ErrorInfo;
import com.taimeitech.pass.dao.appoint.StudentDao;
import com.taimeitech.pass.entity.appoint.StudentBook;
import com.taimeitech.pass.service.appoint.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by devin on 2017/4/7.
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public ActionResult getStudent(long studentId) {
        StudentBook studentBook = studentDao.getStudent(studentId);
        if (studentBook != null) {
            return new ActionResult(true, null, studentBook);
        } else {
            return new ActionResult(false, Arrays.asList(new ErrorInfo(-1, "数据为空")), null);
        }
    }
}
