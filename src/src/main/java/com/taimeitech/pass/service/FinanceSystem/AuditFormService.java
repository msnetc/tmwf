package com.taimeitech.pass.service.FinanceSystem;

import com.taimeitech.pass.dao.FinanceSystem.AuditFormEntity;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class AuditFormService {
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    private void SaveAuditFroms(List<AuditFormEntity> dateItems){
        for (AuditFormEntity form : dateItems) {
            form.setProcessDate(DateTime.now());
            entityManager.persist(form);
        }
    }

    public AuditFormEntity GetAuditForm(Long id){
        return entityManager.find(AuditFormEntity.class, id);
    }
}
