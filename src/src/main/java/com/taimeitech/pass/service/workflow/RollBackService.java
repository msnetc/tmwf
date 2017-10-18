package com.taimeitech.pass.service.workflow;

/**
 * Created by yanjie.miao on 2017/10/18.
 */
public interface RollBackService {
    String rollBackToAssignWorkFlow(String processInstanceId, String userId, String destTaskkey);
}
