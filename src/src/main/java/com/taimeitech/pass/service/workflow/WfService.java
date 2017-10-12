package com.taimeitech.pass.service.workflow;

import com.taimeitech.pass.entity.workflow.*;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

public interface WfService {

    ProcessInstance CreatePi(CreatePI data);

    boolean CompleteTask(CompleteTask data);

    List<HistoricTaskInstance> QueryHistoryTasks(GetHistoryTask data);

    List<Task> QueryTasks(GetTaskList data);

    List<HistoricVariableInstance> GetHistoryVariables(GetHistoryVariables queryParam);

    List<TaskEntity> RollBackTask(String taskId);

    boolean AssignTask(String userId, String taskId);
}
