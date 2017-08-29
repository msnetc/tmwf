package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yanjie.miao on 2017/8/29.
 */
public class GetStartFormData  implements  IReturn<GetStartFormDataResponse>{
    @ApiModelProperty(value = "processDefinitionId")
    private String processDefinitionId;

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }
}
