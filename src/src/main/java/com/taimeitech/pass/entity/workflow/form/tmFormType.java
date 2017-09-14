package com.taimeitech.pass.entity.workflow.form;

import java.text.Format;
import java.util.Map;

/**
 * Created by yanjie.miao on 2017/9/12.
 */
public class tmFormType {
    private String name;
    private Object information;
    private String datePattern;
    private Format dateFormat;
    private Map<String, String> values;
    private String  MimeType;

    public tmFormType(Map<String, String> values) {
        this.values = values;
    }
    public tmFormType(){

    }

    public String getMimeType() {
        return MimeType;
    }

    public void setMimeType(String mimeType) {
        MimeType = mimeType;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    public Format getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(Format dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Object getInformation() {
        return information;
    }

    public void setInformation(Object information) {
        this.information = information;
    }
}
