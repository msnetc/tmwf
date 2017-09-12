package com.taimeitech.pass.api.ui;

import com.taimeitech.pass.entity.Finance.QueryUserTask;
import com.taimeitech.pass.entity.Finance.QueryUserTaskResponse;

import com.taimeitech.pass.entity.workflow.*;
import com.taimeitech.pass.service.workflow.WfService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
https://stackoverflow.com/questions/16017763/how-to-get-the-submitted-form-values-in-external-form-rendering-concept
*/
@Controller
public class TaskFormController {
    @Autowired
    private FormService formService;
    @Autowired
    private TaskService taskService;


    private static String TASK_LIST = "redirect:/task/list";

    @ApiOperation(value = "获取某个pi的start form表单")
    @RequestMapping(value = "/task/GetStartFormData/{taskId}")
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

    /**
     * 读取启动流程的表单字段
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/task/completeFormTask/{taskId}")
    @Transactional
    public String completeTask(@PathVariable("taskId") String taskId, HttpServletRequest request) {
        TaskFormData taskFormData = formService.getTaskFormData(taskId);
        String formKey = taskFormData.getFormKey();
        // 从请求中获取表单字段的值
        List<FormProperty> formProperties = taskFormData.getFormProperties();
        Map<String, String> formValues = new HashMap<String, String>();

        if (StringUtils.isNotBlank(formKey)) { // formkey表单
            Map<String, String[]> parameterMap = request.getParameterMap();
            Set<Map.Entry<String, String[]>> entrySet = parameterMap.entrySet();
            for (Map.Entry<String, String[]> entry : entrySet) {
                String key = entry.getKey();
                formValues.put(key, entry.getValue()[0]);
            }
        } else { // 动态表单
            for (FormProperty formProperty : formProperties) {
                if (formProperty.isWritable()) {
                    String value = request.getParameter(formProperty.getId());
                    formValues.put(formProperty.getId(), value);
                }
            }
        }
        formService.submitTaskFormData(taskId, formValues);
        return TASK_LIST;
    }

    /**
     * 读取启动流程的表单字段
     */
    @RequestMapping(value = "/task/list/{userId}")
    public ModelAndView todoTasks(@PathVariable("userId") String userId) {
        String viewName = "/task-list";
        ModelAndView mav = new ModelAndView(viewName);
        // 5.16版本可以使用一下代码待办查询
        List<Task> allTasks = taskService.createTaskQuery().taskCandidateOrAssigned(userId).list();
        mav.addObject("tasks", allTasks);
        return mav;
    }

}
