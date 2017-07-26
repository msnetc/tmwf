package com.taimeitech.pass.entity.member;

import io.swagger.annotations.ApiModelProperty;

public class  UserGroupRelation{
    @ApiModelProperty(value = "userId")
    private String userId;
    @ApiModelProperty(value = "groupId")
    private String groupId;

    @ApiModelProperty(value = "user和group添加关系1，删除关系0")
    private int addRelation;

    public int getAddRelation() {
        return addRelation;
    }

    public void setAddRelation(int addRelation) {
        this.addRelation = addRelation;
    }

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
