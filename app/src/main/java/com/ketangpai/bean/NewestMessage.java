package com.ketangpai.bean;

/**
 * Created by nan on 2016/5/3.
 */
public class NewestMessage extends MessageInfo {
    private String receive_path;

    public String getReceive_path() {
        return receive_path;
    }

    public void setReceive_path(String receive_path) {
        this.receive_path = receive_path;
    }

    public void updateMessageinfo(MessageInfo messageInfo){
        setReceive_account(messageInfo.getReceive_account());
        setReceive_name(messageInfo.getReceive_name());
        setSend_name(messageInfo.getSend_name());
        setSend_account(messageInfo.getSend_account());
        setSend_path(messageInfo.getSend_path());
        setContent(messageInfo.getContent());
        setTime(messageInfo.getTime());
    }
}
