package com.taimeitech.pass.entity.workflow.form;

import io.swagger.annotations.ApiModelProperty;
import org.activiti.engine.form.FormType;

public class tmFormProperty {

    @ApiModelProperty(value = "字段id")
    private String id;

    @ApiModelProperty(value = "name")
    private  String name;

    @ApiModelProperty(value = "表单formkey")
    private tmFormType type;

    @ApiModelProperty(value = "value")
    private String value;

    @ApiModelProperty(value = "是否可读")
    private boolean readable;

    @ApiModelProperty(value = "是否可写")
    private boolean writable;

    @ApiModelProperty(value = "是否必须")
    private boolean required;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public tmFormType getType() {
        return type;
    }

    public void setType(tmFormType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    public boolean isWritable() {
        return writable;
    }

    public void setWritable(boolean writable) {
        this.writable = writable;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
