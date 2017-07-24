package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * Created by yanjie.miao on 2017/7/21.
 */
public class GetTaskListResponse {
    @ApiModelProperty(value = "总数")
    private int total;

    @ApiModelProperty(value = "task数据" )
    private List<tmTask> data;

    public List<tmTask> getData() {
        return data;
    }

    public void setData(List<tmTask> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
