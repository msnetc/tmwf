package com.taimeitech.pass.service.FinanceSystem;


import com.taimeitech.framework.util.StringUtil;
import com.taimeitech.pass.entity.Finance.CompleteTasks;
import com.taimeitech.pass.entity.Finance.QueryUserAllTask;
        import com.taimeitech.pass.entity.Finance.QueryUserTask;
        import com.taimeitech.pass.entity.Finance.UserTask;
        import com.taimeitech.pass.entity.workflow.HistoryTask;
        import org.activiti.engine.*;
        import org.activiti.engine.history.HistoricTaskInstance;
        import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableInstanceQuery;
        import org.activiti.engine.task.Task;
        import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;
        import org.springframework.beans.BeanUtils;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
        import java.util.List;

public interface FinanceService {

    List<UserTask> GetUserTasks(QueryUserAllTask queryParm);

    List<UserTask> GetUserTasks(QueryUserTask queryParm);

    boolean CompleteTasks(CompleteTasks data);
}
