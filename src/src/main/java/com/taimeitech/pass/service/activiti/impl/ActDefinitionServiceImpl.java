package com.taimeitech.pass.service.activiti.impl;

import java.util.*;

import com.taimeitech.pass.entity.activiti.ActTransition;
import com.taimeitech.pass.service.activiti.ActDefinitionService;
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
public class ActDefinitionServiceImpl implements ActDefinitionService {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;
    /**
     * 查询任务对应的下一步路由
     * @param taskId
     * @return
     */
    @Override
    public List<ActTransition> getApprovalInfo(String taskId){
        List<ActTransition> approvalList=new ArrayList<ActTransition>();
        //获取当前环节对象
        ActivityImpl activityImpl=this.getActivity(taskId);
        List<PvmTransition> outTransitions = activityImpl
                .getOutgoingTransitions();// 获取从某个节点出来的所有线路
        for (PvmTransition tr : outTransitions) {
            ActTransition transition=getTransition(tr);
            //transition.getTaskRoleList();  //获取角色 集合
            approvalList.add(transition);
        }
        return approvalList;
    }
    
    /**
     * 根据TaskId获取流程定义环节对象
     * @param taskId
     * @return
     */
    @Override
    public ActivityImpl getActivity(String taskId){
        Task task =taskService.createTaskQuery().taskId(taskId).singleResult();
        if(task!=null){
            ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                    .getDeployedProcessDefinition(task.getProcessDefinitionId());
            return  def.findActivity(task.getTaskDefinitionKey());
        }
        return null;
    }
    /**
     * 根据路由Id获取路由对象
     * @param transitionId
     * @return ActTransition
     */
    @Override
    public ActTransition getTransition(String processDefinitionId, String transitionId){
        if(StringUtils.isNotEmpty(processDefinitionId) && StringUtils.isNotEmpty(transitionId)){
            ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                    .getDeployedProcessDefinition(processDefinitionId);
            List<ActivityImpl> activityList=def.getActivities();
            for(ActivityImpl activityImpl : activityList){
                List<PvmTransition> outTransitions=activityImpl.getOutgoingTransitions();
                for (PvmTransition pvmTransition : outTransitions) {
                    if(transitionId.equals(pvmTransition.getId())){
                        ActTransition actTransition=getTransition(pvmTransition);
                        return actTransition;
                    }
                }
            }
        }
        return null;
    }
    /**
     * 根据连线对象获取下一步处理信息
     * @param pvmTransition
     * @return ActTransition
     */
    private ActTransition getTransition(PvmTransition pvmTransition){
        PvmActivity nextActivity = pvmTransition.getDestination();
        ActTransition actTransition=new ActTransition();
        actTransition.setId(pvmTransition.getId());
        actTransition.setName(pvmTransition.getProperty("name")+"");
        actTransition.setDescription(pvmTransition.getProperty("documentation")+"");
        actTransition.setCondition(pvmTransition.getProperty("conditionText")+"");
        actTransition.setTaskName(nextActivity.getProperty("name")+"");
        //判断是否是UserTask
        ActivityImpl activity=(ActivityImpl)nextActivity;
        ActivityBehavior activityBehavior = activity.getActivityBehavior();
        if (activityBehavior instanceof UserTaskActivityBehavior || activityBehavior instanceof MultiInstanceActivityBehavior) {
            TaskDefinition taskDefinition=(TaskDefinition)nextActivity.getProperty("taskDefinition");
            if(taskDefinition!=null){
                Set<Expression>  roleSet=taskDefinition.getCandidateGroupIdExpressions();
                //List 用户存放下一步审批角色
                List<String> roleList=new ArrayList<String>();
                for(Expression expression:roleSet){
                    roleList.add(expression.getExpressionText());
                }
                actTransition.setTaskRoleList(roleList);
            }
        }
        return actTransition;
    }
    /**
     * 获取首环节处理角色
     * @return
     */
    @Override
    public List<String> getFristNodeRole(String procDefKey){
        List<String> roleList=new ArrayList<String>();
        ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionKey(procDefKey)
                .orderByProcessDefinitionVersion().desc().list().get(0);
        ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(processDefinition.getId());
        List<ActivityImpl> activityList=def.getActivities();
        for(ActivityImpl activityImpl : activityList){
            ActivityBehavior activityBehavior = activityImpl.getActivityBehavior();
            if (activityBehavior instanceof NoneStartEventActivityBehavior) {
                PvmActivity fristTask=activityImpl.getOutgoingTransitions().get(0).getDestination();
                TaskDefinition taskDefinition=(TaskDefinition)fristTask.getProperty("taskDefinition");
                Set<Expression>  roleSet=taskDefinition.getCandidateGroupIdExpressions();
                for(Expression expression:roleSet){
                    roleList.add(expression.getExpressionText());
                }
                break;
            }
        }
        return roleList;
    }

    /**
     * 获取流程审批信息，提供发送时 展示选环节列表
     * @param taskId
     * @return
     */
    @Override
    public List<Map<String, Object>> getApprovalTrack(String taskId){
        if(StringUtils.isNotBlank(taskId)){
            //1：获取路由信息
            List<ActTransition> approvalList=getApprovalInfo(taskId); //actTaskService.getApprovalInfo(act.getTaskId());
            //2:迭代路由
            if(approvalList!=null){
                //构建树形Tree-List
                List<Map<String, Object>> resultList=new ArrayList<Map<String, Object>>();
                for (ActTransition transition : approvalList) {
                    Map<String, Object> tempMap=new HashMap<String, Object>();
                    tempMap.put("id",transition.getId());
                    tempMap.put("name", transition.getTaskName());
                    tempMap.put("pId",1);
                    //此处还有验证路由条件的控制，暂时没有实现
                    //此处可以自己行增加 路由对应的人员信息，可根据角色进行解析，此示例只需要进行选路由即可
                    resultList.add(tempMap);
                }
                Map<String, Object> rootMap=new HashMap<String, Object>();
                rootMap.put("id","1");
                if(resultList.size()==0){
                    rootMap.put("name","暂无处理环节");
                }else{
                    rootMap.put("name","可被选择的处理环节");
                }
                rootMap.put("pId","0");
                rootMap.put("nocheck",true);//设置根节点不可选中
                resultList.add(rootMap);
                return resultList;
            }
        }
        return null;
    }
}