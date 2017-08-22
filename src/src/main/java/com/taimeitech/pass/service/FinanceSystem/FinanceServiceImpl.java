package com.taimeitech.pass.service.FinanceSystem;

import com.taimeitech.framework.util.StringUtil;
import com.taimeitech.pass.entity.Finance.*;
import com.taimeitech.pass.entity.workflow.HistoryTask;
import com.taimeitech.pass.entity.workflow.IReturn;
import org.activiti.engine.*;
        import org.activiti.engine.history.HistoricTaskInstance;
        import org.activiti.engine.history.HistoricTaskInstanceQuery;
        import org.activiti.engine.history.HistoricVariableInstance;
        import org.activiti.engine.history.HistoricVariableInstanceQuery;
        import org.activiti.engine.task.Task;
        import org.activiti.engine.task.TaskQuery;
        import org.apache.commons.lang.NotImplementedException;
        import org.apache.commons.lang.StringUtils;
        import org.springframework.beans.BeanUtils;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.ArrayList;
        import java.util.List;

@Service
public class FinanceServiceImpl implements FinanceService{

    @Autowired
    private HistoryService historyService;
    @Autowired
    private TaskService taskService;

    public List<UserTask> GetUserTasks(QueryUserAllTask queryParm){

        TaskQuery runTimeTaskQuery = taskService.createTaskQuery();
        HistoricTaskInstanceQuery historyTaskQuery = historyService.createHistoricTaskInstanceQuery(); // 创建历史任务实例查询
        HistoricVariableInstanceQuery variableHistoryQuery =historyService.createHistoricVariableInstanceQuery();

        if(StringUtils.isNotEmpty(queryParm.getPdId())){
            runTimeTaskQuery.processDefinitionKeyLikeIgnoreCase(queryParm.getPdId().toUpperCase());
            historyTaskQuery.processDefinitionKeyLikeIgnoreCase(queryParm.getPdId().toUpperCase());
        }
        if(StringUtils.isNotEmpty(queryParm.getUserId())){
            runTimeTaskQuery.taskAssignee(queryParm.getUserId());
            historyTaskQuery.taskAssignee(queryParm.getUserId());
        }
        List<Task> runTimeTasks = runTimeTaskQuery.list();
        List<HistoricTaskInstance>  historyTasks = historyTaskQuery.list();

        List<UserTask> ret = new ArrayList<>();

        for (Task t : runTimeTasks) {
            UserTask item = new UserTask();
            BeanUtils.copyProperties(t, item);

            item.setTaskId(t.getId());
            item.setTaskName(t.getName());
            item.setCommitDate(t.getCreateTime());
            item.setTaskStatusId(0);
            ret.add(item);
        }
        for (HistoricTaskInstance t : historyTasks) {
            UserTask item = new UserTask();
            BeanUtils.copyProperties(t, item);
            item.setTaskId(t.getId());
            item.setTaskName(t.getName());
            item.setCommitDate(t.getCreateTime());
            item.setTaskStatusId(1);
            variableHistoryQuery = variableHistoryQuery.taskId(t.getId());
            //.variableNameLike("approved");
            List<HistoricVariableInstance> list =variableHistoryQuery.list();

            if(list !=null && list.isEmpty() ==false){
                HistoricVariableInstance apprvoed = list.get(0);
                if(apprvoed == null) return ret;
                String approved = apprvoed.getValue().toString();
                if(approved.toUpperCase() =="TRUE"){
                    item.setApproved(1);
                }else{
                    item.setApproved(0);
                }
            }
            ret.add(item);

        }
        return ret;
    }

    public List<UserTask> GetUserTasks(QueryUserTask queryParm){
        TaskQuery runTimeTaskQuery = taskService.createTaskQuery();
//        HistoricTaskInstanceQuery historyTaskQuery = historyService.createHistoricTaskInstanceQuery(); // 创建历史任务实例查询
//        HistoricVariableInstanceQuery variableHistoryQuery =historyService.createHistoricVariableInstanceQuery();

        if(StringUtils.isNotEmpty(queryParm.getPdId())){
            runTimeTaskQuery.processDefinitionKeyLikeIgnoreCase(queryParm.getPdId().toUpperCase());
        }
        if (StringUtil.isNotBlank(queryParm.getUserId())) {
            runTimeTaskQuery=runTimeTaskQuery.taskAssignee(queryParm.getUserId());
        }
        List<Task> runTimeTasks = runTimeTaskQuery.list();
        List<UserTask> ret = new ArrayList<>();

        for (Task t : runTimeTasks) {
            UserTask item = new UserTask();
            BeanUtils.copyProperties(t, item);
            item.setTaskId(t.getId());
            item.setTaskName(t.getName());
            ret.add(item);
        }
        return ret;
    }

    @Transactional
    public boolean CompleteTasks(CompleteTasks data) {
        for (CompleteUserTask task:data.getUserTasks()){
            taskService.claim(task.getTaskId(), task.getUserId());
            taskService.complete(task.getTaskId(), task.getVariables());
        }
        return true;
    }

}
