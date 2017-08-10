package com.taimeitech.pass.entity.workflow;

import com.taimeitech.pass.entity.PagerQuery;
import io.swagger.annotations.ApiModelProperty;

public class GetTaskList extends PagerQuery implements IReturn<GetTaskListResponse> {

    @ApiModelProperty(value = "任务名称")
    private String taskName;
    @ApiModelProperty(value = "PdId", notes = "报价单QuotationInvoice0808, 项目任务跟踪ProjectTaskTrace0808, 工时表跟踪LaborHourTrace0808")
    private String PdId;
    @ApiModelProperty(value = "processInstanceId")
    private String processInstanceId;

    @ApiModelProperty(value = "用户id")
    private String userId;

    public String getPdId() {
        return PdId;
    }

    public void setPdId(String pdId) {
        PdId = pdId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
