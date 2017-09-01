package com.taimeitech.pass.api.ui;

import com.taimeitech.pass.entity.Finance.QueryUserTask;
import com.taimeitech.pass.entity.Finance.QueryUserTaskResponse;

import com.taimeitech.pass.entity.workflow.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/*
https://stackoverflow.com/questions/16017763/how-to-get-the-submitted-form-values-in-external-form-rendering-concept
*/
@Controller
@RequestMapping(value = "/form")
public class TaskFormController {
    @Autowired
    private FormService formService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;

    @ApiOperation(value = "获取某个task的form表单")
    @RequestMapping(value = "/getUserTaskFormData/{query}")
    public ModelAndView Post(@PathVariable("query")  GetUserTaskFormData query) {
        String viewName = "task-form";
        ModelAndView mav = new ModelAndView(viewName);
        TaskFormData taskFormData = formService.getTaskFormData(query.getTaskId());
        if (taskFormData != null) return mav;
        mav.addObject("taskFormData", taskFormData);
        return mav;
    }

    @ApiOperation(value = "获取某个pi的start form表单")
    @RequestMapping(value = "/GetStartFormData/{taskId}")
    public ModelAndView Post(@PathVariable("taskId") String taskId) {
        String viewName = "task-form";
        ModelAndView mav = new ModelAndView(viewName);
        TaskFormData taskFormData = formService.getTaskFormData(taskId);
        if (taskFormData.getFormKey() != null) {
            Object renderedTaskForm = formService.getRenderedTaskForm(taskId);
            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
            mav.addObject("task", task);
            mav.addObject("taskFormData", renderedTaskForm);
            mav.addObject("hasFormKey", true);
            return mav;
        }
        else {
            mav.addObject("taskFormData", taskFormData);
        }
        return mav;
    }

}
