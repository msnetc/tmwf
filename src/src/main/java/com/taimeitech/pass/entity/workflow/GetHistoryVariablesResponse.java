package com.taimeitech.pass.entity.workflow;

import java.util.Map;

/**
 * Created by yanjie.miao on 2017/7/21.
 */
public class GetHistoryVariablesResponse {

    private Map<String, Object> variables;

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }
}
