package com.taimeitech.pass.api.ui;

import com.taimeitech.framework.common.TaimeiLogger;
import com.taimeitech.pass.utils.InputStreamUtils;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

import static com.taimeitech.pass.utils.InputStreamUtils.InputStreamTOString;

/**
 * 部署流程
 */
@Controller
public class DeploymentController  {
    @Autowired
    private  RepositoryService repositoryService;

    @RequestMapping(value = "/deployment/index")
    public ModelAndView Index() {
        ModelAndView mav = new ModelAndView("deploy");
        return mav;
    }

    @RequestMapping(value = "/deployment/deploy", method = RequestMethod.POST)
    public String deploy(@RequestParam(value = "file", required = true) MultipartFile file) {
        // 获取上传的文件名
        String fileName = file.getOriginalFilename();
        try {
            // 得到输入流（字节流）对象
            InputStream fileInputStream = file.getInputStream();
            String bpmnFile = InputStreamUtils.InputStreamTOString(fileInputStream);
            DeploymentBuilder deployment = repositoryService.createDeployment();
            deployment.addString(fileName, bpmnFile);
            Deployment deploy = deployment.deploy();
        } catch (Exception e) {
            TaimeiLogger.error("error on deploy process, because of file input stream");
        }
        return "redirect:/activiti/processes";
    }

    @RequestMapping(value="deployment/upload", method=RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request) throws Exception
    {
        Part part = request.getPart("file");
        try {
            String talentId = request.getParameter("tenantId");
            InputStream fileInputStream = part.getInputStream();
            String fileName = part.getSubmittedFileName();
            // 文件的扩展名
            String extension=  FilenameUtils.getExtension(fileName);
            // zip或者bar类型的文件用ZipInputStream方式部署
            DeploymentBuilder deployment = repositoryService.createDeployment();
            deployment.tenantId(talentId);
            if (extension.equals("zip") || extension.equals("bar")) {
                ZipInputStream zip = new ZipInputStream(fileInputStream);
                deployment.addZipInputStream(zip);
            } else {
                // 其他类型的文件直接部署
                deployment.addInputStream(fileName, fileInputStream);
            }
            fileInputStream.close();
        } catch (Exception e) {
            TaimeiLogger.error("error on deploy process, because of file input stream");
        }
        return "redirect:/activiti/processes";
    }

    /**
     * 读取流程资源
     *
     * @param processDefinitionId 流程定义ID
     * @param resourceName        资源名称
     */
    @RequestMapping(value = "/deployment/read-resource")
    public void readResource(@RequestParam("pdid") String processDefinitionId, @RequestParam("resourceName") String resourceName, HttpServletResponse response)
            throws Exception {
        ProcessDefinitionQuery pdq = repositoryService.createProcessDefinitionQuery();
        ProcessDefinition pd = pdq.processDefinitionId(processDefinitionId).singleResult();

        // 通过接口读取
        InputStream resourceAsStream = repositoryService.getResourceAsStream(pd.getDeploymentId(), resourceName);

        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }
    /**
     * 删除部署的流程，级联删除流程实例
     *
     * @param deploymentId 流程部署ID
     */
    @RequestMapping(value = "/deployment/delete-deployment")
    public String deleteProcessDefinition(@RequestParam("deploymentId") String deploymentId) {
        repositoryService.deleteDeployment(deploymentId, true);
        return "redirect:/activiti/processes";
    }



    @Bean
    MultipartConfigElement createMultipartConfigElement()
    {
        MultipartConfigFactory mcf = new MultipartConfigFactory();
        /**
         * 设置最大上传文件的大小，默认是10MB
         */
        mcf.setMaxFileSize("50MB");
        return mcf.createMultipartConfig();
    }



}
