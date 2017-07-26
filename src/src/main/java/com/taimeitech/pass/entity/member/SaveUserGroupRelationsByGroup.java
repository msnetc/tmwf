package com.taimeitech.pass.entity.member;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yanjie.miao on 2017/7/26.
 */
public class SaveUserGroupRelationsByGroup  {
    @ApiModelProperty(value = "appId")
    public String appId;
    @ApiModelProperty(value = "groupId")
    public String groupId;

    @ApiModelProperty(value = "userIds")
    public List<String> userIds;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
}
