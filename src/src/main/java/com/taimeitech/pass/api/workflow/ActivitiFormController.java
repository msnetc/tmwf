package com.taimeitech.pass.api.workflow;

import com.taimeitech.framework.common.dto.ActionResult;
import com.taimeitech.pass.entity.workflow.GetTaskListResponse;
import com.taimeitech.pass.entity.workflow.form.*;
import com.taimeitech.pass.entity.workflow.tmTask;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.activiti.engine.FormService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ActivitiFormController {

    @Autowired
    private FormService formService;
    @Autowired
    private TaskService taskService;

    @ApiOperation(value = "获取某个pi的start form表单")
    @RequestMapping(value = "taskform/GetTaskForm", method = RequestMethod.POST)
    public GetTaskFormResponse Post(@ApiParam("data") @RequestBody GetTaskForm data) {
        GetTaskFormResponse response = new GetTaskFormResponse();
        UserTaskForm taskForm =new UserTaskForm();
        response.setData(taskForm);

        String taskId  = data.getTaskId();
        TaskFormData taskFormData = formService.getTaskFormData(taskId);
        tmTaskFormData tmTaskFormData =toTaskFormData(taskFormData);

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        tmTask taskData =totmTask(task);
        taskForm.setTask(taskData);
        if (taskFormData.getFormKey() != null) {
            Object renderedTaskForm = formService.getRenderedTaskForm(taskId);
            taskForm.setFormData(renderedTaskForm);
       }
        else
        {
            taskForm.setFormData(tmTaskFormData);
        }
        response.setSuccess(true);
        return response;
    }

    public static  tmTask  totmTask(Task t){
        tmTask task = new tmTask();
        BeanUtils.copyProperties(t, task);
        task.setTaskId(t.getId());
        task.setAssignee(t.getAssignee());
        return task;
    }

    public static tmTaskFormData toTaskFormData(TaskFormData formData){
        tmTaskFormData data = new tmTaskFormData();
        BeanUtils.copyProperties(formData, data);
        return data;
    }
}