package com.taimeitech.pass.entity.workflow.form;

import com.taimeitech.pass.entity.workflow.IReturn;
import io.swagger.annotations.ApiModelProperty;


public class GetTodoTasks   {
    @ApiModelProperty(value = "userId")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
