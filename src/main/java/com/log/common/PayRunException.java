package com.log.common;

/**
 * Created by wodezuiaishinageren on 2017/11/23.
 * 运行时异常
 */
public class PayRunException extends  RuntimeException {
    /***
     * 错误编码
     */
    private String errorCode;

    /**
     * 构造描述信息
     * @param message
     */
    public PayRunException(String message){
        super(message);
    }

    /**
     * 构造基本异常
     * @param errorCode 错误码
     * @param message 描述信息
     */
    public PayRunException(String errorCode,String message){
        super(message);
        this.setErrorCode(errorCode);
    }



    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
    public static void main(String [] args){
        try {
            int i=1;
            int a=i/0;
        } catch (PayRunException e) {
            throw new PayRunException("002",e.getMessage());
        }
    }
}
