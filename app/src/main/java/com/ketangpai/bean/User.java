package com.ketangpai.bean;

import cn.bmob.v3.BmobObject;

public class User extends BmobObject {


    protected String account;
    protected String password;
    protected String school;
    protected String name;
    protected Integer type;
    private Integer number;
    private String path = "";

    public User(String account, String name, String path) {
        this.name = name;
        this.path = path;
        this.account = account;
    }

    public User() {
        super();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


}
