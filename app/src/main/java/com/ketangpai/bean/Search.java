package com.ketangpai.bean;

/**
 * Created by nan on 2016/5/9.
 */
public class Search {
    /**
     * 1 作业 2 公告 3 测试
     */
    private int type;
    private int type_id;
    private String content;
    private String title;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
