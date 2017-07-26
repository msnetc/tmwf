package com.taimeitech.pass.entity.member;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yanjie.miao on 2017/7/24.
 */
public class DeleteUserGroup {
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "用户名称")
    private String groupId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
