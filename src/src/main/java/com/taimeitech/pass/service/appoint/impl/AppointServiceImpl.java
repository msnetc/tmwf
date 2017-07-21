package com.taimeitech.pass.service.appoint.impl;

import com.taimeitech.framework.common.TaimeiLogger;
import com.taimeitech.framework.common.dto.ActionResult;
import com.taimeitech.framework.common.dto.ErrorInfo;
import com.taimeitech.pass.common.enums.AppointStateEnum;
import com.taimeitech.pass.common.exception.AppointException;
import com.taimeitech.pass.common.exception.NoNumberException;
import com.taimeitech.pass.common.exception.RepeatAppointException;
import com.taimeitech.pass.dao.appoint.AppointDao;
import com.taimeitech.pass.dao.appoint.BookDao;
import com.taimeitech.pass.dto.AppointExecution;
import com.taimeitech.pass.entity.appoint.Appoint;
import com.taimeitech.pass.service.appoint.AppointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * Created by devin on 2017/4/7.
 */
@Service
public class AppointServiceImpl implements AppointService {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private AppointDao appointDao;

    public ActionResult getAppoint(long bookId, long studentId) {
        Appoint appoint = appointDao.getAppoint(bookId, studentId);

        if (appoint != null) {
            return new ActionResult(true, null, appoint);
        } else {
            return new ActionResult(false, Arrays.asList(new ErrorInfo(-1, "数据为空")), null);
        }
    }

    /**
     * 使用注解控制事务方法的优点： 1.开发团队达成一致约定，明确标注事务方法的编程风格
     * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
     * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
     * Transactions belong on the Service layer
     */
    @Transactional
    @Override
    public AppointExecution insertAppoint(long bookId, long studentId) {
        try {
            // 减库存
            int update = bookDao.updateBookNumber(bookId);
            if (update <= 0) {// 库存不足
                throw new NoNumberException("no number");
            } else {
                // 执行预约操作
                int insert = appointDao.insertAppoint(new Appoint(bookId, studentId));
                if (insert <= 0) {// 重复预约
                    throw new RepeatAppointException("repeat appoint");
                } else {// 预约成功
                    Appoint appoint = appointDao.getAppoint(bookId, studentId);
                    return new AppointExecution(bookId, AppointStateEnum.SUCCESS, appoint);
                }
            }
        } catch (NoNumberException e1) {
            throw e1;
        } catch (RepeatAppointException e2) {
            throw e2;
        } catch (Exception e) {
            TaimeiLogger.error(e);
            throw new AppointException("appoint inner error:" + e.getMessage());
        }
    }

}
