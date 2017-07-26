package com.taimeitech.pass.entity.member;

import io.swagger.annotations.ApiModelProperty;

public class WfGroup {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "用户组描述信息")
    private String name;

    @ApiModelProperty(value = "appId")
    private String appId;

    @ApiModelProperty(value = "用户组类型")
    private String type;

    public String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
