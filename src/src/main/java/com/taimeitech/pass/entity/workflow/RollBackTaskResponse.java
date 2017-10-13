package com.taimeitech.pass.entity.workflow;

import com.taimeitech.framework.common.dto.ActionResult;
import io.swagger.annotations.ApiModelProperty;

public class RollBackTaskResponse extends ActionResult {

    @ApiModelProperty(value = "恢复生成的任务")
    private tmTask task;

    public tmTask getTask() {
        return task;
    }

    public void setTask(tmTask task) {
        this.task = task;
    }
}
