package com.taimeitech.pass.entity.workflow;

import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

public class CreatePI {
    @ApiModelProperty(value = "bpmn流程定义中的id" ,notes="发布到工作流中心的bpmn文件中的processid")
    private String businessProcessId;

    @ApiModelProperty(value = "业务ID",notes="例如要申请请假，那么先填写登记信息，然后（保存+启动流程），因为请假是单独设计的数据表，所以保存后得到实体ID就可以把它传给processInstanceBusinessKey方法启动流程")
    private String businessKey;

    @ApiModelProperty(value = "传给工作流的变量")
    private Map<String, String> variables;

    public String getBusinessProcessName() {
        return businessProcessId;
    }

    public void setBusinessProcessName(String businessProcessName) {
        this.businessProcessId = businessProcessName;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Map<String, String> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }
}
