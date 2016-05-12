package com.ketangpai.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by nan on 2016/4/25.
 */
public class NotificationInfo {
    private int type_id;

    private int type;
    private String content;
    private String c_name;


    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    /**
     * 1 公告 2 作业 3 资料 4测试
     *
     * @return
     */
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }
}
