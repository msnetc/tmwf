package com.taimeitech.pass.entity.member;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yanjie.miao on 2017/7/25.
 */
public class DeleteGroups {
    @ApiModelProperty(value = "group的id列表")
    private List<String> groupIds;

    public List<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }

}
