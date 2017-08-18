package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;
import org.activiti.engine.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yanjie.miao on 2017/8/16.
 */
public class GetUserTaskFormData  implements  IReturn<GetUserTaskFormDataResponse> {

    @Autowired
    @ApiModelProperty(value = "taskId")
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

}
