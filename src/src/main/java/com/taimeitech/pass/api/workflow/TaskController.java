package com.taimeitech.pass.api.workflow;

import com.taimeitech.pass.entity.Finance.QueryUserTask;
import com.taimeitech.pass.entity.Finance.QueryUserTaskResponse;

import com.taimeitech.pass.entity.workflow.GetUserTaskFormData;
import com.taimeitech.pass.entity.workflow.GetUserTaskFormDataResponse;
import com.taimeitech.pass.entity.workflow.UserTaskFormData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private FormService formService;

    @RequestMapping(value = "task/getform", method = RequestMethod.POST)
    @ApiOperation(value = "获取某个task的form表单", notes = "获取某个task的form表单")
    public GetUserTaskFormDataResponse readTaskForm(@ApiParam("query") @RequestBody GetUserTaskFormData query){
        GetUserTaskFormDataResponse response = new GetUserTaskFormDataResponse();
        UserTaskFormData data = new UserTaskFormData();
        response.setData(data);
        TaskFormData taskFormData = formService.getTaskFormData(query.getTaskId());
        data.setFormData(taskFormData);
        if (taskFormData.getFormKey() != null) {
            Object renderedTaskForm = formService.getRenderedTaskForm(query.getTaskId());
            Task task = taskService.createTaskQuery().taskId(query.getTaskId()).singleResult();
            if(task == null) return response;
            data.setTask(task);
        }
        return response;
    }
}
