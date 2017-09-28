package com.taimeitech.pass.workflowExt.Listener.PiEndListener;

/**
 * Created by yanjie.miao on 2017/9/28.
 */
public enum  PiEndEnum {
   //成功
    Sucess(10),
    //停止
    Stop(20),
    //拒绝
    Reject(30),
    //失败
    Failure(40);

    private int code;

    private PiEndEnum(int _code) {
        this.code = _code;
    }

    @Override
    public String toString() {
        return String.valueOf(this.code);

    }
}
