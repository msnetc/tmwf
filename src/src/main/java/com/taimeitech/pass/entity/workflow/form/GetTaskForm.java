package com.taimeitech.pass.entity.workflow.form;

import com.taimeitech.pass.entity.workflow.IReturn;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yanjie.miao on 2017/8/29.
 */
public class GetTaskForm  implements IReturn<GetTaskFormResponse> {
    @ApiModelProperty(value = "taskId")
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
