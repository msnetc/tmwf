package com.taimeitech.pass.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Created by zhengjun.zhang on 2017/6/19.
 */
public class Goods {
    private int goodsId;
    @Max(20)
    @NotNull
    private int goodsNumber;
    @NotEmpty
    private String goodsName;

    public Goods() {
    }

    public Goods(int goodsId, int goodsNumber, String goodsName) {
        this.goodsId = goodsId;
        this.goodsNumber = goodsNumber;
        this.goodsName = goodsName;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
