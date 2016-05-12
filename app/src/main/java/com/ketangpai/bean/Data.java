package com.ketangpai.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by nan on 2016/4/24.
 */
public class Data extends BmobObject {
    private String size;
    private String name;
    private String url;
    private Integer c_id;
    private Integer d_id;

    public Integer getD_id() {
        return d_id;
    }

    public void setD_id(Integer d_id) {
        this.d_id = d_id;
    }

    public Data() {
    }

    public Data(String size, String name) {
        this.size = size;
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }
}
