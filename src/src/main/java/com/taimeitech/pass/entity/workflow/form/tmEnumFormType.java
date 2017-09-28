package com.taimeitech.pass.entity.workflow.form;

import org.activiti.engine.impl.form.EnumFormType;

import java.util.LinkedHashMap;

public class tmEnumFormType   {

    private String id;
    private String name;
    private boolean isRequired;
    private boolean isReadable;
    private boolean isWritable;
    private LinkedHashMap<Object, Object> values;
    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

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

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public boolean isReadable() {
        return isReadable;
    }

    public void setReadable(boolean readable) {
        isReadable = readable;
    }

    public boolean isWritable() {
        return isWritable;
    }

    public void setWritable(boolean writable) {
        isWritable = writable;
    }





    public LinkedHashMap<Object, Object> getValues() {
        return values;
    }

    public void setValues(LinkedHashMap<Object, Object> values) {
        this.values = values;
    }
}
