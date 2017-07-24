package com.taimeitech.pass.entity.workflow;

import com.sun.org.apache.bcel.internal.generic.IREM;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yanjie.miao on 2017/7/21.
 */
public class GetHistoryVariables implements IReturn<GetHistoryVariablesResponse>{
    @ApiModelProperty(value = "processInstanceId")
    public String  processInstanceId;
    @ApiModelProperty(value = "taskId"  )
    public String taskId;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
