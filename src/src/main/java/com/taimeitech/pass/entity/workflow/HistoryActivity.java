package com.taimeitech.pass.entity.workflow;

import org.joda.time.DateTime;

/**
 * Created by yanjie.miao on 2017/7/24.
 */
public class HistoryActivity {
    private String activitiId;
    private String activitiName;
    private String type;
    private String pid;
    private String assignee;
    private DateTime startTime;
    private DateTime endTime;
    private long duration;


    public String getActivitiId() {
        return activitiId;
    }

    public void setActivitiId(String activitiId) {
        this.activitiId = activitiId;
    }

    public String getActivitiName() {
        return activitiName;
    }

    public void setActivitiName(String activitiName) {
        this.activitiName = activitiName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
