package com.taimeitech.pass.entity.workflow;

import com.taimeitech.pass.entity.PagerQuery;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

public class GetHistoryTask extends PagerQuery{
    @ApiModelProperty(value = "办理人id")
    private String userId;
    @ApiModelProperty(value = "任务名称")
    private String taskName;
    @ApiModelProperty(value = "processInstanceId")
    private String processInstanceId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

}
