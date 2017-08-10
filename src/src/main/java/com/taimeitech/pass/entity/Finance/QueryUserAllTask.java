package com.taimeitech.pass.entity.Finance;

import com.taimeitech.pass.entity.workflow.IReturn;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yanjie.miao on 2017/8/10.
 */
public class QueryUserAllTask  implements IReturn<QueryUserAllTaskResponse> {

    @ApiModelProperty(value = "processInstanceId 列表")
   private List<String> processInstanceIds;

    public List<String> getProcessInstanceIds() {
        return processInstanceIds;
    }

    public void setProcessInstanceIds(List<String> processInstanceIds) {
        this.processInstanceIds = processInstanceIds;
    }
}
