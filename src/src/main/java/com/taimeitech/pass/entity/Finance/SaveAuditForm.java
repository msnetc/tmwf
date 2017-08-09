package com.taimeitech.pass.entity.Finance;

import com.taimeitech.pass.dao.FinanceSystem.AuditFormEntity;
import com.taimeitech.pass.entity.workflow.IReturn;

import java.util.List;

/**
 * Created by yanjie.miao on 2017/8/9.
 */
public class SaveAuditForm   implements IReturn<SaveAuditFormResponse> {
    public List<AuditFormEntity> data;
}
