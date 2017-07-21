package com.taimeitech.pass.entity.mall;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by devin on 2017/5/13.
 */
public class Shop implements Serializable {
    private int shopId;
    private String shopName;
    private Timestamp cstCreate;
    private List<Product> products;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Timestamp getCstCreate() {
        return cstCreate;
    }

    public void setCstCreate(Timestamp cstCreate) {
        this.cstCreate = cstCreate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
