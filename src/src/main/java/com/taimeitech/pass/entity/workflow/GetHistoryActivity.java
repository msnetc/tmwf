package com.taimeitech.pass.entity.workflow;

import org.joda.time.DateTime;

/**
 * Created by yanjie.miao on 2017/7/24.
 */
public class GetHistoryActivity {
 private String  processInstanceId;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
}
