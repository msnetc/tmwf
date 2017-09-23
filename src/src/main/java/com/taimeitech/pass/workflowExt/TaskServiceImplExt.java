package com.taimeitech.pass.workflowExt;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.Map;

@Transactional
public class TaskServiceImplExt implements TaskServiceExt {

    @Autowired
    private TaskService taskService;
    @Override
    public boolean rejecttoPreTask(String taskId, Map<String, Object> variables) {
        return true;
    }

    @Override
    public boolean rejecttoPreTask(String taskId) {
        return false;
    }

    @Override
    public boolean rejecttoPreTask(String taskId, Map<String, Object> variables, String rejectReason, String bussinessop, String bussinessRemark) {
        return false;
    }

    @Override
    public boolean rejecttoPreTask(String taskId, String rejectReason, String bussinessop, String bussinessRemark) {
        return false;
    }
}
