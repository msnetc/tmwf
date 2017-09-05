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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class FinanceServiceImpl implements FinanceService{

    @Autowired
    private HistoryService historyService;
    @Autowired
    private TaskService taskService;

    public List<UserTask> GetUserTasks(QueryUserAllTask queryParm){

        HistoricTaskInstanceQuery historyTaskQuery = historyService.createHistoricTaskInstanceQuery(); // 创建历史任务实例查询
        HistoricVariableInstanceQuery variableHistoryQuery =historyService.createHistoricVariableInstanceQuery();
        if(StringUtils.isNotEmpty(queryParm.getPdId())){
            historyTaskQuery.processDefinitionKeyLikeIgnoreCase(queryParm.getPdId().toUpperCase());
        }
        if(StringUtils.isNotEmpty(queryParm.getUserId())){
            historyTaskQuery.taskAssignee(queryParm.getUserId());
        }
        List<HistoricTaskInstance>  historyTasks = historyTaskQuery.list();
        List<HistoricVariableInstance> variables  = variableHistoryQuery.variableName("approved").list();

        List<UserTask> ret = new ArrayList<>();
        for (HistoricTaskInstance t : historyTasks) {

            UserTask item = new UserTask();
            BeanUtils.copyProperties(t, item);
            item.setTaskId(t.getId());
            item.setTaskName(t.getName());
            item.setCommitDate(FormtDate(t.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(t.getEndTime() == null){
                item.setTaskStatusId(0);
            }
            else{
                item.setTaskStatusId(1);
            }
            if(variables !=null && variables.size() >0) {
                for(HistoricVariableInstance hvi:variables){
                    String varibaleName = hvi.getVariableName();
                    if(varibaleName.equalsIgnoreCase("APPROVED") == false) continue;;
                    String approved = hvi.getValue().toString();
                    if(approved.equalsIgnoreCase("TRUE")){
                        item.setApproved(1);
                    }else{
                        item.setApproved(0);
                    }
                }
            }
            ret.add(item);
        }
        return ret;
    }

    private String FormtDate(Date date, String dtFormat){
        DateFormat df = new SimpleDateFormat(dtFormat);
        String retDate = df.format(date);
        return retDate;
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

    public boolean CompleteTasks(CompleteTasks data) {
        for (CompleteUserTask task:data.getUserTasks()){
            taskService.claim(task.getTaskId(), task.getUserId());
            taskService.complete(task.getTaskId(), task.getVariables());
        }
        return true;
    }

}
