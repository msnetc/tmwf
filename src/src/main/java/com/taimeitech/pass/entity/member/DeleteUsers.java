package com.taimeitech.pass.entity.member;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yanjie.miao on 2017/7/25.
 */
public class DeleteUsers {
    @ApiModelProperty(value = "要删除的用户列表")
    private List<String> userIds;

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
}
