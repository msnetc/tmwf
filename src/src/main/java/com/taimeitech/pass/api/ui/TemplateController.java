package com.taimeitech.pass.api.ui;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TemplateController {

    /**
     * 返回html模板
     */
    @RequestMapping(value = "/helloHtml",method = RequestMethod.GET)
    public String helloHtml(Map<String,Object> map){
        map.put("hello","from TemplateController.helloHtml");
        return "helloHtml";
    }

}
