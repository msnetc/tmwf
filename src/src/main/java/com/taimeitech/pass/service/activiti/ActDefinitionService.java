package com.taimeitech.pass.service.activiti;


import java.util.*;

import com.taimeitech.pass.entity.activiti.ActTransition;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.MultiInstanceActivityBehavior;
import org.activiti.engine.impl.bpmn.behavior.NoneStartEventActivityBehavior;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 流程定义Service
 * @author Ruoli
 * 2016.08.23
 */
@Service
@Transactional(readOnly = true)
public interface ActDefinitionService {
    List<ActTransition> getApprovalInfo(String taskId);

    ActivityImpl getActivity(String taskId);

    ActTransition getTransition(String processDefinitionId, String transitionId);

    List<String> getFristNodeRole(String procDefKey);

    List<Map<String, Object>> getApprovalTrack(String taskId);
}