package com.taimeitech.pass.api.mall;

import com.taimeitech.framework.common.dto.ActionResult;
import com.taimeitech.pass.entity.Goods;
import com.taimeitech.pass.service.mall.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * Created by devin on 2017/5/13.
 */
@Validated
@RestController
@RefreshScope
public class UserController {
    @Autowired
    private UserService userService;

    @Value("${nihao.cn.value}")
    private String nihao;

    @ApiOperation(value="获取所有用户", notes="分页获取用户详细信息")
    @GetMapping(value = "listUser")
    public ActionResult listUser(@Min(value = 1,message = "最小值是1") @RequestParam int pageIndex,
                                 @RequestParam int pageSize) {
        System.out.println("t:"+nihao);
        return userService.listUser(pageIndex, pageSize);
    }

    // http://localhost:3001/testValidation?goodsId=1&goodsNumber=20&goodsName=iPhone
    @GetMapping("testValidation")
    public ActionResult testValidation(@Valid @ModelAttribute Goods goods){
        return new ActionResult(true, null, goods);
    }
}
