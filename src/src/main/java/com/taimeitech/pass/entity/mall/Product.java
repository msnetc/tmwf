package com.taimeitech.pass.entity.mall;

import java.io.Serializable;

/**
 * Created by devin on 2017/5/13.
 */
public class Product implements Serializable {
    private int productId;
    private String productName;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
