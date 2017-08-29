package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yanjie.miao on 2017/8/29.
 */
public class GetStartFormData  implements  IReturn<GetStartFormDataResponse>{
    @ApiModelProperty(value = "taskId")
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
