package com.taimeitech.pass.workflowExt;

import java.util.Map;


public interface TaskServiceExt{
    /**
     * 将当前任务驳回到上一个任务处理人处，并更新流程变量参数
     * @param taskId
     * @param variables
     * @return
     */
    boolean rejecttoPreTask(String taskId, Map<String, Object> variables);

    /**
     * 将当前任务驳回到上一个任务处理人处
     * @param taskId
     */
    boolean rejecttoPreTask(String taskId);

    /**
     * 将当前任务驳回到上一个任务处理人处，并更新流程变量参数
     * @param taskId
     * @param variables
     * @return
     */
    boolean rejecttoPreTask(String taskId, Map<String, Object> variables, String rejectReason, String bussinessop, String bussinessRemark);

    /**
     * 将当前任务驳回到上一个任务处理人处
     * @param taskId
     */
    boolean rejecttoPreTask(String taskId,String rejectReason,String bussinessop,String bussinessRemark);
}