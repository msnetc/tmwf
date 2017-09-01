package com.taimeitech.pass.mapper;

import com.taimeitech.framework.common.mybatis.BaseMapper;
import com.taimeitech.pass.dao.FinanceSystem.AuditFormEntity;

import java.util.List;

public interface AuditFormMapper  extends BaseMapper<AuditFormEntity> {
    List<AuditFormEntity> listByUserId();
}
