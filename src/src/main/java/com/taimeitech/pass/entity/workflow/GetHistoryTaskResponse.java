package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * Created by yanjie.miao on 2017/7/24.
 */
public class GetHistoryTaskResponse {
    @ApiModelProperty(value = "总数")
    private int total;
    @ApiModelProperty(value = "data")
    private List<HistoryTask> data;

    public List<HistoryTask> getData() {
        return data;
    }

    public void setData(List<HistoryTask> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
