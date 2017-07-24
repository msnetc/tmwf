package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

public class GetHistoryTask {
    @ApiModelProperty(value = "办理人id")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
