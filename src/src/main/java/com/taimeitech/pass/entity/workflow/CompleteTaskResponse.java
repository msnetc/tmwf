package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yanjie.miao on 2017/7/24.
 */
public class CompleteTaskResponse {
    @ApiModelProperty(value = "是否成功")
    private boolean hasSuccess;
    @ApiModelProperty(value = "如果发生错误，错误信息")
    private String message;

    public boolean isHasSuccess() {
        return hasSuccess;
    }

    public void setHasSuccess(boolean hasSuccess) {
        this.hasSuccess = hasSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
