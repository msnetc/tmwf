package com.taimeitech.pass.workflowExt.Listener;

import com.taimeitech.framework.common.TaimeiLogger;
import com.taimeitech.framework.util.HttpUtils;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
public class TaskEndListener  implements TaskListener {
    private static final long serialVersionUID = 1L;
    @Override
    public void notify(DelegateTask delegateTask) {
        String endUrl = (String) delegateTask.getVariable("endTaskUrl");
        Map<String, String> header = new HashMap<String, String>();
        Map<String, String> map = new HashMap<String, String>();
        String processIntanceId = delegateTask.getProcessInstanceId();
        map.put("ProcessInstanceId", processIntanceId);
        try {
            String responseData = HttpUtils.post(endUrl, header, map);
        }catch(IOException e){
            TaimeiLogger.error(e);
        }
    }
}
