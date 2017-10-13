package com.taimeitech.pass.entity.workflow;

/**
 * Created by yanjie.miao on 2017/10/13.
 */
public class tmTaskUser implements java.io.Serializable{
    private static final long serialVersionUID = 8379071759772449529L;

    private String userId;
    private String userName;

    public tmTaskUser(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
