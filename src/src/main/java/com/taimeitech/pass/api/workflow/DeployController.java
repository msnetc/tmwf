package com.taimeitech.pass.api.workflow;

import com.taimeitech.framework.common.TaimeiLogger;
import com.taimeitech.framework.common.dto.ActionResult;
import com.taimeitech.framework.common.dto.ErrorInfo;
import com.taimeitech.pass.entity.workflow.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

@RestController
public class DeployController {
    @Autowired
    private RepositoryService repositoryService;

    @ApiOperation(value = "部署流程资源")
    @RequestMapping(value = "/deployment/deployFile", method = {RequestMethod.POST})
    public ActionResult<String> deployInFile(@ApiParam(value = "deployParam", required = true) @RequestBody DeployParam deployParam) {

        ActionResult response = new ActionResult();
        response.setSuccess(false);

        String filePath=deployParam.getFilePath();
        String fileName=FilenameUtils.getName(filePath);
        Path path = Paths.get(filePath);
        if (Files.notExists(path)) {
            response.addError(0,filePath+" 此文件路径不存在文件");
            return response;
        }
        try {
            // 得到输入流（字节流）对象
            InputStream fileInputStream = new FileInputStream(filePath);
            // 文件的扩展名
            String extension = FilenameUtils.getExtension(filePath);
            // zip或者bar类型的文件用ZipInputStream方式部署
            DeploymentBuilder deployment = repositoryService.createDeployment();
            deployment.tenantId(deployParam.getTalentId());

            if (extension.equals("zip") || extension.equals("bar")) {
                ZipInputStream zip = new ZipInputStream(fileInputStream);
                deployment.addZipInputStream(zip);
            } else {
                // 其他类型的文件直接部署
                deployment.addInputStream(fileName, fileInputStream);
            }
            Deployment dp = deployment.deploy();
            String id = dp.getId();
            response.setData(id);
        } catch (Exception e) {
            TaimeiLogger.error("error on deploy process, because of file input stream");
            return  response;
        }
        response.setSuccess(true);
        return response;
    }



}
