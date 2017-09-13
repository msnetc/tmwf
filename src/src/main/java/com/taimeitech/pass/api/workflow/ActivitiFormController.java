package com.taimeitech.pass.api.workflow;

import com.taimeitech.framework.common.dto.ActionResult;
import com.taimeitech.pass.entity.org.GetUsers;
import com.taimeitech.pass.entity.org.tmCompany;
import com.taimeitech.pass.entity.org.tmOrganization;
import com.taimeitech.pass.entity.org.tmUser;
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

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class ActivitiFormController {

    @Autowired
    private FormService formService;
    @Autowired
    private TaskService taskService;

    @ApiOperation(value = "获取某个pi的start form表单")
    @RequestMapping(value = "taskform/GetTaskForm", method = {RequestMethod.POST, RequestMethod.OPTIONS})
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
        else{
            taskForm.setFormData(tmTaskFormData);
        }
        response.setSuccess(true);
        return response;
    }

    @ApiOperation(value = "获取某个租户的用户列表")
    @RequestMapping(value = "taskform/GetUsers", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    public ActionResult<tmCompany> Post(@ApiParam("data") @RequestBody GetUsers data ){
        ActionResult<tmCompany> response = new ActionResult<>();
        tmCompany company = mockGetCompany(data.getTenantId());
        response.setData(company);
        return response;
    }

    private tmCompany mockGetCompany(String talentId){
        tmCompany c = new tmCompany();
        c.setId(UUID.randomUUID().toString());
        c.setName("中国化工");
        List<tmOrganization> orgs = new ArrayList<>();
        c.setOrganizations(orgs);
        for(int j=1; j<3; j++){
            tmOrganization o1= new tmOrganization();
            o1.setId("dept"+j);
            o1.setName("部门"+j);
            orgs.add(o1);
            List<tmUser> users1 = new ArrayList<>();
            for(int i=0;i<10;i++){
                tmUser u1 = new tmUser();
                u1.setId(UUID.randomUUID().toString());
                u1.setName("name"+i);
                users1.add(u1);
            }
            o1.setUserList(users1);
        }
        return  c;
    }

    private   tmTask  totmTask(Task t){
        tmTask task = new tmTask();
        BeanUtils.copyProperties(t, task);
        task.setTaskId(t.getId());
        task.setAssignee(t.getAssignee());
        return task;
    }

    private  tmTaskFormData toTaskFormData(TaskFormData formData){
        tmTaskFormData data = new tmTaskFormData();
        BeanUtils.copyProperties(formData, data);
        return data;
    }

}