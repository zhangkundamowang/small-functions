package com.zk.mybatisplus.common.utils;

public class ReturnResponse {

    /**
     * 操作是否成功，默认成功
     */
    private boolean success = true;

    /**
     * 返回的信息，默认操作成功
     */
    private String message = "操作成功！";

    private String messageTitle ;

    /**
     * 返回结果数据
     */
    private Object result;

    /**
     * 默认构造函数，属性为默认值<br/>
     * <ul>
     * <li>isSuccess: true</li>
     * <li>message： 操作成功！</li>
     * <li>result： null</li>
     * </ul>
     */
    public ReturnResponse() {}

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    @Override
    public String toString() {
        return "ReturnResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", messageTitle='" + messageTitle + '\'' +
                ", result=" + result +
                '}';
    }
}
