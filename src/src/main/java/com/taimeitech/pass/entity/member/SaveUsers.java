package com.taimeitech.pass.entity.member;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yanjie.miao on 2017/7/25.
 */
public class SaveUsers {
    @ApiModelProperty(value = "用户列表")
    private List<WfUser> data;

    public List<WfUser> getData() {
        return data;
    }

    public void setData(List<WfUser> data) {
        this.data = data;
    }
}
