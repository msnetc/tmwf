package com.taimeitech.pass.entity.Finance;

import com.taimeitech.pass.entity.workflow.IReturn;
import io.swagger.annotations.ApiModelProperty;

public class QueryUserTask implements IReturn<QueryUserTaskResponse> {
    @ApiModelProperty(value = "")
    private String pdId;
    @ApiModelProperty(value = "group的id列表")
    private int taskStatusId;

    public String getPdId() {
        return pdId;
    }

    public void setPdId(String pdId) {
        this.pdId = pdId;
    }

    public int getTaskStatusId() {
        return taskStatusId;
    }

    public void setTaskStatusId(int taskStatusId) {
        this.taskStatusId = taskStatusId;
    }
    
}
