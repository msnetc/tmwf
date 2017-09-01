package com.taimeitech.pass.entity.approval;

import com.taimeitech.pass.entity.workflow.IReturn;
import io.swagger.annotations.ApiModelProperty;
public class GetAllUserTask implements IReturn<GetAllUserTaskResponse>{
    @ApiModelProperty(value = "pdId 报价单QuotationInvoice0808, 项目任务跟踪ProjectTaskTrace0808, 工时表跟踪LaborHourTrace0808" )
    private String pdId;

    @ApiModelProperty(value = "userId")
    private String userId;

    public String getPdId() {
        return pdId;
    }

    public void setPdId(String pdId) {
        this.pdId = pdId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
