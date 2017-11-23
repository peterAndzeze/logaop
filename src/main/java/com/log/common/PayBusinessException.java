package com.log.common;

/**
 * Created by wodezuiaishinageren on 2017/11/23.
 */
public class PayBusinessException extends  Exception {
    /***
     * 错误码
     */
    private String errorCode;

    public PayBusinessException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
