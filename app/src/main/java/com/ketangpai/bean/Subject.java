package com.ketangpai.bean;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;

/**
 * Created by nan on 2016/5/5.
 */
public class Subject extends BmobObject {
    private Integer s_id;
    private String title;
    private List<String> content;
    /**
     * 类型 1 判断题 2 单选题 3 多选题 4 简答题
     */
    private Integer type;
    private String answer;
    private String solution;
    /**
     * 得分
     */
    private Integer score;
    /**
     * 分值
     */
    private Integer s_value;

    private boolean submit=false;
    private boolean check=false;

    public Subject() {
        content = new ArrayList<>();
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isSubmit() {
        return submit;
    }


    public void setSubmit(boolean submit) {
        this.submit = submit;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getS_value() {
        return s_value;
    }

    public void setS_value(Integer s_value) {
        this.s_value = s_value;
    }

    public Integer getS_id() {
        return s_id;
    }

    public void setS_id(Integer s_id) {
        this.s_id = s_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
