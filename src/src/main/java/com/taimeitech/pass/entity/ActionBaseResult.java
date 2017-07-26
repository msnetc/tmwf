package com.taimeitech.pass.entity;

import com.taimeitech.framework.common.dto.ErrorInfo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ActionBaseResult {
    @ApiModelProperty("是否成功")
    private boolean success;
    @ApiModelProperty("错误消息")
    private List<ErrorInfo> errors;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ErrorInfo> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorInfo> errors) {
        this.errors = errors;
    }
}
