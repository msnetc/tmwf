package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by yanjie.miao on 2017/7/21.
 */
public class WorkFlowTask {

    @ApiModelProperty(value = "任务id")
     private String id;

    @ApiModelProperty(value = "任务名称")
    private String name;

    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @ApiModelProperty(value = "结束日期")
    private Date endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
