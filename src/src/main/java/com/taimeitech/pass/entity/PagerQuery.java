package com.taimeitech.pass.entity;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * Created by yanjie.miao on 2017/7/26.
 */
public class PagerQuery {


    @ApiModelProperty(value = "pageIndex")
    private int pageIndex;


    @ApiModelProperty(value = "pagesize")
    private int pageSize;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
