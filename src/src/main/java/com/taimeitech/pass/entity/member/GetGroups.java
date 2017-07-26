package com.taimeitech.pass.entity.member;

import com.taimeitech.pass.entity.workflow.IReturn;
import io.swagger.annotations.ApiModelProperty;

public class GetGroups implements IReturn<GetGroupsResponse> {

    @ApiModelProperty(value = "租户id")
    private String tenantId;

    @ApiModelProperty(value = "appId")
    private String appId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
