package com.taimeitech.pass.service.FinanceSystem;

import com.taimeitech.pass.dao.FinanceSystem.AuditFormEntity;
import com.taimeitech.pass.entity.Finance.AuditFormDto;
import com.taimeitech.pass.mapper.AuditFormMapper;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
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
    @Autowired
    private TaskService taskService;


    public boolean SaveAuditFormList(List<AuditFormEntity> dateItems){
        TaskQuery runTimeTaskQuery = taskService.createTaskQuery();
        for (AuditFormEntity af :dateItems) {
            Task task = runTimeTaskQuery.taskId(af.getTaskId()).singleResult();
            if(task == null) continue;
            af.setTaskName(task.getName());
            af.setProcessDate(DateTime.now().toDate());
            af.setTask_assign_date(task.getCreateTime());
            af.setProcessInstanceId(task.getProcessInstanceId());
            af.setCreateTime(DateTime.now().toDate());
            af.setUpdateTime(DateTime.now().toDate());
            af.setCreateBy(af.getProcessUserId());
            af.setUpdateBy(af.getProcessUserId());
        }
        auditFormMapper.batchInsert(dateItems);
        return true;
    }

    public AuditFormEntity GetAuditForm(String id){
        AuditFormEntity obj =  auditFormMapper.get(id);
        return obj;
    }

}
