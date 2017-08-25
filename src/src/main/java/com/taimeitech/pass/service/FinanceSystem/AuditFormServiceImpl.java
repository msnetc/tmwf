package com.taimeitech.pass.service.FinanceSystem;

import com.taimeitech.pass.dao.FinanceSystem.AuditFormEntity;
import com.taimeitech.pass.entity.Finance.AuditFormDto;
import com.taimeitech.pass.mapper.AuditFormMapper;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class AuditFormServiceImpl implements  AuditFormService {
    @Autowired
    private AuditFormMapper auditFormMapper;

    public boolean SaveAuditFormList(List<AuditFormEntity> dateItems){
        for (AuditFormEntity af :dateItems) {
            af.setCreateTime(DateTime.now().toDate());
            af.setUpdateTime(DateTime.now().toDate());
        }
        auditFormMapper.batchInsert(dateItems);
        return true;
    }

    public AuditFormEntity GetAuditForm(String id){
        AuditFormEntity obj =  auditFormMapper.get(id);
        return obj;
    }

}
