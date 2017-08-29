package com.taimeitech.pass.api.workflow;

import com.taimeitech.pass.entity.Finance.QueryUserTask;
import com.taimeitech.pass.entity.Finance.QueryUserTaskResponse;

import com.taimeitech.pass.entity.workflow.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/*
https://stackoverflow.com/questions/16017763/how-to-get-the-submitted-form-values-in-external-form-rendering-concept
*/
@RestController
public class TaskFormController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private FormService formService;

    @ApiOperation(value = "获取某个task的form表单")
    @RequestMapping(value = "task/form/getUserTaskFormData", method = RequestMethod.POST)
    public GetUserTaskFormDataResponse Post(@ApiParam("query") @RequestBody GetUserTaskFormData query){
        GetUserTaskFormDataResponse response = new GetUserTaskFormDataResponse();
        TaskFormData taskFormData = formService.getTaskFormData(query.getTaskId());
        if(taskFormData != null) return response;
        response.setData(taskFormData);
        return response;
    }


    @ApiOperation(value = "获取某个pi的start form表单")
    @RequestMapping(value = "task/form/GetStartFormData", method = RequestMethod.POST)
    public GetStartFormDataResponse Post(@ApiParam("query") @RequestBody GetStartFormData query){
        GetStartFormDataResponse response = new GetStartFormDataResponse();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(query.getProcessDefinitionId()).singleResult();
        boolean hasStartFormKey = processDefinition.hasStartFormKey();
        if(hasStartFormKey == false)  return response;
        
        Object renderedStartForm = formService.getRenderedStartForm(query.getProcessDefinitionId());
        if(renderedStartForm != null) return response;
        response.setData(renderedStartForm);
        return response;
    }
    }
