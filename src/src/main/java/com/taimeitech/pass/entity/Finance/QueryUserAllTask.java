package com.taimeitech.pass.entity.Finance;

import com.taimeitech.pass.entity.workflow.IReturn;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yanjie.miao on 2017/8/10.
 */
public class QueryUserAllTask  implements IReturn<QueryUserAllTaskResponse> {

    @ApiModelProperty(value = "pdId 报价单QuotationInvoice0808, 项目任务跟踪ProjectTaskTrace0808, 工时表跟踪LaborHourTrace0808" )
    private String pdId;

    @ApiModelProperty(value = "userId")
    private String userId;

    @ApiModelProperty(value = "businessKey")
    private String businessKey;


    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

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
