package com.taimeitech.pass.service.FinanceSystem;

import com.taimeitech.pass.dao.FinanceSystem.AuditFormEntity;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface AuditFormService {

    void SaveAuditFroms(List<AuditFormEntity> dateItems);

    AuditFormEntity GetAuditForm(String id);

}
