package com.taimeitech.pass.entity.Finance;

import com.taimeitech.pass.entity.workflow.IReturn;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yanjie.miao on 2017/8/10.
 */
public class QueryUserAllTask  implements IReturn<QueryUserAllTaskResponse> {

    @ApiModelProperty(value = "businessKeyId")
    private String businessKeyId;
    @ApiModelProperty(value = "用户id")
    private String userId;

    public String getBusinessKeyId() {
        return businessKeyId;
    }

    public void setBusinessKeyId(String businessKeyId) {
        this.businessKeyId = businessKeyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
