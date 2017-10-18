package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yanjie.miao on 2017/10/16.
 */
public class JumpTask implements IReturn<JumpTaskResponse>{

    @ApiModelProperty(value = "当前任务id")
    private String curentTaskId;


    @ApiModelProperty(value = "目标活动id")
    private String destinationActivityId;

    @ApiModelProperty(value = "用户id")
    private String userId;


    public String getCurentTaskId() {
        return curentTaskId;
    }

    public void setCurentTaskId(String curentTaskId) {
        this.curentTaskId = curentTaskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDestinationActivityId() {
        return destinationActivityId;
    }

    public void setDestinationActivityId(String destinationActivityId) {
        this.destinationActivityId = destinationActivityId;
    }
}
