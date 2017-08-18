package com.taimeitech.pass.entity.Finance;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by yanjie.miao on 2017/8/14.
 */
public class CompleteTasks {

    @ApiModelProperty(value = "任务列表")
    private List<CompleteUserTask> userTasks;

    public List<CompleteUserTask> getUserTasks() {
        return userTasks;
    }

    public void setUserTasks(List<CompleteUserTask> userTasks) {
        this.userTasks = userTasks;
    }
}
