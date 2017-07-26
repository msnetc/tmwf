package com.taimeitech.pass.entity.member;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SaveGroups {
    @ApiModelProperty(value = "group列表")
    public List<WfGroup> data;

    public List<WfGroup> getData() {
        return data;
    }

    public void setData(List<WfGroup> data) {
        this.data = data;
    }
}
