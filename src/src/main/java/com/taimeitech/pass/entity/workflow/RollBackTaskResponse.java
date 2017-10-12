package com.taimeitech.pass.entity.workflow;

import com.taimeitech.framework.common.dto.ActionResult;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yanjie.miao on 2017/10/9.
 */
public class RollBackTaskResponse extends ActionResult {


    @ApiModelProperty(value = "恢复生成的任务列表")
    private List<tmTask> tasks;

    public List<tmTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<tmTask> tasks) {
        this.tasks = tasks;
    }
}
