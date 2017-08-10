package com.taimeitech.pass.entity.Finance;

import com.taimeitech.pass.entity.workflow.IReturn;
import io.swagger.annotations.ApiModelProperty;

public class QueryUserTask implements IReturn<QueryUserTaskResponse> {

    @ApiModelProperty(value = "PdId", notes = "报价单QuotationInvoice0808, 项目任务跟踪ProjectTaskTrace0808, 工时表跟踪LaborHourTrace0808")
    private String PdId;

    @ApiModelProperty(value = "用户id")
    private String userId;

    public String getPdId() {
        return PdId;
    }

    public void setPdId(String pdId) {
        PdId = pdId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
