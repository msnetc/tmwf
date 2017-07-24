package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yanjie.miao on 2017/7/21.
 */
public class GetTaskList implements IReturn<GetTaskListResponse> {


    @ApiModelProperty(value = "pageIndex")
    private int pageIndex;


    @ApiModelProperty(value = "pagesize")
      private int pageSize;

    @ApiModelProperty(value = "taskName")
     private String taskName;

    @ApiModelProperty(value = "userId")
     private String userId;

    @ApiModelProperty(value = "roleId")
    private String roleId;


    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
