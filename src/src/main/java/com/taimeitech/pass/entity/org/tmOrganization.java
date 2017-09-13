package com.taimeitech.pass.entity.org;

import java.util.List;

/**
 * Created by yanjie.miao on 2017/9/13.
 */
public class tmOrganization {
    private String id;
    private String name;
    private List<tmUser> userList;


    public List<tmUser> getUserList() {
        return userList;
    }

    public void setUserList(List<tmUser> userList) {
        this.userList = userList;
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
}
