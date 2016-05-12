package com.ketangpai.bean;

/**
 * Created by nan on 2016/5/2.
 */
public class PushMessage {
    /**
     * 0为课程消息 1为聊天信息
     */
    private int type;
    /**
     * 类
     */
    private String object;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
