package com.taimeitech.pass.service.FinanceSystem;

import com.taimeitech.pass.dao.FinanceSystem.AuditFormEntity;
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
public class AuditFormServiceImpl implements  AuditFormService {
    @Autowired
    private AuditFormMapper auditFormMapper;

    @Transactional
    public void SaveAuditFroms(List<AuditFormEntity> dateItems){
            auditFormMapper.batchInsert(dateItems);
    }

    public AuditFormEntity GetAuditForm(String id){
        AuditFormEntity obj =  auditFormMapper.get(id);
        return obj;
    }
}
