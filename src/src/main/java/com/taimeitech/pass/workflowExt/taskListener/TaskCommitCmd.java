package com.taimeitech.pass.workflowExt.taskListener;

import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.cmd.NeedsActiveTaskCmd;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
//import org.activiti.engine.impl.pvm.process.ActivityImpl;
//import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;

//http://blog.csdn.net/wkwanglei/article/details/25811281

/**
 * 任务提交到任意节点
 * @author wangzhe
 * @date 2014-5-14
 */
public class TaskCommitCmd  extends NeedsActiveTaskCmd<Void> {

    private static final long serialVersionUID = 1L;

    /**
     * 目标任务的定义Id
     */
    private String toTaskKey;
    /**
     * 参数
     */
    protected Map variables;
    /**
     * jump跳跃 ，turnback 退回（）
     */
    protected String type;


    public TaskCommitCmd(String _taskId, String _toTaskKey, String _type, Map _variables) {
        super(_taskId);
        this.toTaskKey = _toTaskKey;
        this.type = _type;
        this.variables = _variables;

    }

    @Override
    protected Void execute(CommandContext commandContext, TaskEntity task) {

        if (variables != null) task.setExecutionVariables(variables);
        ExecutionEntity execution = task.getExecution();
        //流程定义id
        String procDefId = execution.getProcessDefinitionId();

        return null;
    }
}

