package com.taimeitech.pass.api.appoint;

import com.taimeitech.framework.common.dto.ActionResult;
import com.taimeitech.framework.common.dto.ErrorInfo;
import com.taimeitech.framework.util.StringUtil;
import com.taimeitech.pass.common.enums.AppointStateEnum;
import com.taimeitech.pass.common.exception.NoNumberException;
import com.taimeitech.pass.common.exception.RepeatAppointException;
import com.taimeitech.pass.dto.AppointExecution;
import com.taimeitech.pass.service.appoint.AppointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

// url:/模块/资源/{id}/细分
@RestController
//@RequestMapping("/moduleName")
public class AppointController {

    @Autowired
    private AppointService appointService;

    @PostMapping(value = "/appoints")
    private ActionResult<AppointExecution> appointBook(HttpServletRequest request) {
        //application/x-www-form-urlencoded方式提交
        String bookIdStr = request.getParameter("bookId");
        String sutdentIdStr = request.getParameter("studentId");
        if (StringUtil.isParamsEmpty(bookIdStr, sutdentIdStr)) {
            return new ActionResult(false, Arrays.asList(new ErrorInfo(-2, "参数缺失")), null);
        }
        long bookId = StringUtil.stringToLong(bookIdStr);
        long studentId = StringUtil.stringToLong(sutdentIdStr);

        AppointExecution execution = null;
        try {
            execution = appointService.insertAppoint(bookId, studentId);
        } catch (NoNumberException e1) {
            execution = new AppointExecution(bookId, AppointStateEnum.NO_NUMBER);
        } catch (RepeatAppointException e2) {
            execution = new AppointExecution(bookId, AppointStateEnum.REPEAT_APPOINT);
        } catch (Exception e) {
            execution = new AppointExecution(bookId, AppointStateEnum.INNER_ERROR);
        }

        return new ActionResult<AppointExecution>(true, null, execution);
    }

    @GetMapping(value = "/appoints")
    private ActionResult getAppoint(HttpServletRequest request) {
        String bookIdStr = request.getParameter("bookId");
        String sutdentIdStr = request.getParameter("studentId");
        if (StringUtil.isParamsEmpty(bookIdStr, sutdentIdStr)) {
            return new ActionResult(false, Arrays.asList(new ErrorInfo(-2, "参数缺失")), null);
        }
        long bookId = StringUtil.stringToLong(bookIdStr);
        long studentId = StringUtil.stringToLong(sutdentIdStr);

        return appointService.getAppoint(bookId, studentId);
    }

}
