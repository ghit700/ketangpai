package com.ketangpai.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/4/19.
 */
public class Course extends BmobObject {

    private Integer c_id;
    private String name;
    private String code;
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }
}
