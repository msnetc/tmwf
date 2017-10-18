package com.taimeitech.pass.service.workflow.impl;

import com.taimeitech.pass.service.workflow.RollBackService;
import org.activiti.engine.*;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RollBackServiceImpl  implements RollBackService{


    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private HistoryService historyService;


    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryServivce;

    @Override
    @Transactional
    public String rollBackToAssignWorkFlow(String processInstanceId, String userId, String destTaskkey) {

        Map<String, Object> variables;
        ExecutionEntity entity = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();
        ProcessDefinitionEntity definition = (ProcessDefinitionEntity)(repositoryServivce.getProcessDefinition(entity.getProcessDefinitionId()));

        variables = entity.getProcessVariables();
//当前活动环节
        ActivityImpl currActivityImpl = definition.findActivity(entity.getActivityId());
//目标活动节点

        ActivityImpl nextActivityImpl = ((ProcessDefinitionImpl) definition).findActivity(destTaskkey);
        Map<String, Object> nextActivityVariables = new HashMap<String, Object>();
        nextActivityVariables.put("assignee", userId);
        nextActivityImpl.setVariables(nextActivityVariables);

        if(currActivityImpl !=null){
//所有的出口集合
            List<PvmTransition> pvmTransitions = currActivityImpl.getOutgoingTransitions();
            List<PvmTransition> oriPvmTransitions = new ArrayList<PvmTransition>();
            for(PvmTransition transition : pvmTransitions){
                oriPvmTransitions.add(transition);
            }
//清除所有出口
            pvmTransitions.clear();
//建立新的出口
            List<TransitionImpl> transitionImpls = new ArrayList<TransitionImpl>();
            TransitionImpl tImpl = currActivityImpl.createOutgoingTransition();
            tImpl.setDestination(nextActivityImpl);

            transitionImpls.add(tImpl);

            List<Task> list = taskService.createTaskQuery().processInstanceId(entity.getProcessInstanceId())
                    .taskDefinitionKey(entity.getActivityId()).list();
            for(Task task:list){
                taskService.complete(task.getId(), variables);
                historyService.deleteHistoricTaskInstance(task.getId());
            }

            for(TransitionImpl transitionImpl:transitionImpls){
                currActivityImpl.getOutgoingTransitions().remove(transitionImpl);
            }

            for(PvmTransition pvmTransition:oriPvmTransitions){
                pvmTransitions.add(pvmTransition);
            }
        }

        List<org.activiti.engine.task.Task>  tasks= taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        if(tasks!=null && tasks.size()>0){
            org.activiti.engine.task.Task  task = tasks.get(0);
            taskService.setAssignee(task.getId(), userId);
            return task.getId();
        }
        return null;
    }

}
