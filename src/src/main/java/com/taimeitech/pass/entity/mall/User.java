package com.taimeitech.pass.entity.mall;

import java.io.Serializable;
import java.util.List;

/**
 * Created by devin on 2017/5/13.
 */
public class User implements Serializable{
    private int userId;
    private String userName;
    private List<Shop> shops;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
