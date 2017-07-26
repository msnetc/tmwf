package com.taimeitech.pass.entity.member;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yanjie.miao on 2017/7/26.
 */
public class SaveUserGroupRelationsByUser {
    @ApiModelProperty(value = "appId")
    public String appId;
    @ApiModelProperty(value = "userId")
    public String userId;

    @ApiModelProperty(value = "groupIds")
    public List<String> groupIds;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }
}
