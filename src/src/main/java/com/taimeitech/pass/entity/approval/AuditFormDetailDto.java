package com.taimeitech.pass.entity.approval;

import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

/**
 * Created by yanjie.miao on 2017/8/31.
 */
public class AuditFormDetailDto extends  AuditFormDto {
    @ApiModelProperty(value = "任务状态")
    protected int processStatusId;

    @ApiModelProperty(value = "提交时间")
    protected DateTime commitDate;

    public DateTime getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(DateTime commitDate) {
        this.commitDate = commitDate;
    }

    public int getProcessStatusId() {
        return processStatusId;
    }

    public void setProcessStatusId(int processStatusId) {
        this.processStatusId = processStatusId;
    }
}
