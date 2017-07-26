package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

/**
 * Created by yanjie.miao on 2017/7/24.
 */
public class HistoryTask {
    @ApiModelProperty("任务ID")
    private String id;
    @ApiModelProperty("流程实例ID")
    private String processInstanceId;
    @ApiModelProperty("活动名称")
    private String activityName;
    @ApiModelProperty("办理人")
    private String assignee;
    @ApiModelProperty("开始时间")
    private DateTime startTime;
    @ApiModelProperty("结束时间")
    private DateTime endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }
}
