package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yanjie.miao on 2017/10/9.
 */
public class RollBackTask implements IReturn<RollBackTaskResponse> {
    @ApiModelProperty(value = "taskId" ,notes="要恢复的任务id，完成后请再调用QueryTask获取新生成的任务id")
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
