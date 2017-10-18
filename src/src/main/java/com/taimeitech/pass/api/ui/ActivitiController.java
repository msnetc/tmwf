package com.taimeitech.pass.api.ui;

import com.taimeitech.framework.common.TaimeiLogger;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

@Controller
@RequestMapping("/activiti")
public class ActivitiController {

    @Autowired
    ManagementService managementService;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;

    @ResponseBody
    @RequestMapping("/engine/info")
    public Map<String, String> engineProperties() {
        return managementService.getProperties();
    }

    @RequestMapping("/processes")
    public ModelAndView processes() {
        ModelAndView mav = new ModelAndView("processes");
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        mav.addObject("processes", list);
        return mav;
    }

    @RequestMapping("/process/start/{processDefinitionId}")
    public String startProcess(@PathVariable("processDefinitionId") String processDefinitionId) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId);
        System.out.println("成功启动了流程：" + processInstance.getId());
        return "redirect:/activiti/tasks";
    }

    @RequestMapping("/tasks")
    public ModelAndView tasks() {
        ModelAndView mav = new ModelAndView("tasks");
        List<Task> list = taskService.createTaskQuery().list();
        mav.addObject("tasks", list);
        return mav;
    }

    @RequestMapping("/task/complete/{taskId}")
    public String completeTask(@PathVariable("taskId") String taskId) {
        taskService.complete(taskId);
        return "redirect:/activiti/tasks";
    }

    @RequestMapping(value="/upload", method= RequestMethod.POST)
    @ResponseBody
    public void deploy(HttpServletRequest request) throws Exception  {
        Part part = request.getPart("file");
        try {
            String talentId = request.getParameter("tenantId");
            InputStream fileInputStream = part.getInputStream();
            String fileName = part.getSubmittedFileName();
            // 文件的扩展名
            String extension=  FilenameUtils.getExtension(fileName);
            // zip或者bar类型的文件用ZipInputStream方式部署
            DeploymentBuilder deployment = repositoryService.createDeployment();
            if (StringUtils.isNotBlank(talentId)) {
                deployment.tenantId(talentId);
            }
            if (extension.equals("zip") || extension.equals("bar")) {
                ZipInputStream zip = new ZipInputStream(fileInputStream);
                deployment.addZipInputStream(zip);
            } else {// 其他类型的文件直接部署
                deployment.addInputStream(fileName, fileInputStream);
            }
            deployment.deploy();
        } catch (Exception e) {
            TaimeiLogger.error(e);
        }
    }

}
