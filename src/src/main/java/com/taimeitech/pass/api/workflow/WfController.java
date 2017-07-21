package com.taimeitech.pass.api.workflow;

import com.taimeitech.pass.entity.workflow.CreatePI;
import com.taimeitech.pass.entity.workflow.CreatePIResponse;
import com.taimeitech.pass.entity.workflow.ReadStartForm;
import com.taimeitech.pass.entity.workflow.ReadStartFormResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yanjie.miao on 2017/7/21.
 */
public class WfController {

    @ApiOperation(value = "创建pi")
    @RequestMapping(value = "process-instance/CreatePI}", method= RequestMethod.POST )
    public CreatePIResponse Post(@ApiParam("pi") @RequestBody CreatePI pi){
     return new CreatePIResponse();
    }

    @ApiOperation(value = "获取表单定义")
    @RequestMapping(value = "process-instance/ReadStartForm}", method= RequestMethod.POST )
    public ReadStartFormResponse Post(@ApiParam("readForm") @RequestBody ReadStartForm readForm){
        return new ReadStartFormResponse();
    }

}
