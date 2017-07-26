package com.taimeitech.pass.common.exception;

/**
 * Created by yanjie.miao on 2017/7/25.
 */
public class OrganizationNotFoundException  extends RuntimeException {

    public OrganizationNotFoundException(String message) {
        super(message);
    }

    public OrganizationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}