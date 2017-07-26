package com.taimeitech.pass.common.exception;

/**
 * Created by yanjie.miao on 2017/7/25.
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
