package com.taimeitech.pass.api.ui;


import com.taimeitech.pass.entity.workflow.form.tmEnumFormType;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.impl.form.EnumFormType;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring4.dialect.SpringStandardDialect;

import java.util.LinkedHashMap;

@Component("FormProperyUtil")
public class FormProperyUtil   {
    public LinkedHashMap<Object, Object> GetEnumItems(FormProperty formProperty){
        if(formProperty.getType() instanceof EnumFormType){
            EnumFormType formData =(EnumFormType) formProperty.getType();
            Object vlaues = formData.getInformation("values");
            LinkedHashMap<Object, Object> items = (LinkedHashMap<Object, Object>)vlaues;
            return items;
        }
        return null;
    }
}
