package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yanjie.miao on 2017/10/12.
 */
public class AssignTask implements IReturn<AssignTaskResponse> {

    @ApiModelProperty(value = "被指派人的用户id")
    private String userId;

    @ApiModelProperty(value = "任务id")
    private String taskId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
