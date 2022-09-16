package com.example.modulebase.bean;

public class BaseBean {

    /**
     * action : ModifyNickOrDes
     * code : S
     * msg : SUCCESS～
     */

    protected String action;
    protected String code;
    protected String msg;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
