package com.ketangpai.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by nan on 2016/5/2.
 */
public class MessageInfo extends BmobObject {
    private Integer m_id;
    private Long time;
    private String content;
    private String send_account;
    private String send_name;
    private String receive_name;
    private String receive_account;
    private String send_path;

    public String getSend_path() {
        return send_path;
    }



    public void setSend_path(String send_path) {
        this.send_path = send_path;
    }

    public Integer getM_id() {
        return m_id;
    }

    public void setM_id(Integer m_id) {
        this.m_id = m_id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSend_account() {
        return send_account;
    }

    public void setSend_account(String send_account) {
        this.send_account = send_account;
    }

    public String getSend_name() {
        return send_name;
    }

    public void setSend_name(String send_name) {
        this.send_name = send_name;
    }

    public String getReceive_name() {
        return receive_name;
    }

    public void setReceive_name(String receive_name) {
        this.receive_name = receive_name;
    }

    public String getReceive_account() {
        return receive_account;
    }

    public void setReceive_account(String receive_account) {
        this.receive_account = receive_account;
    }
}
