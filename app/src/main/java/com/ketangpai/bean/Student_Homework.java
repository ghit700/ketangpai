package com.ketangpai.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by nan on 2016/4/30.
 */
public class Student_Homework extends BmobObject {
    private Integer c_id;
    private Integer h_id;
    private Integer grade;
    private Long commit_time;
    private Integer student_number;
    private String student_name;
    private String account;
    /**
     * 未交,按时交,逾期未交
     */
    private String s_state = "未交";
    /**
     * 批改,未批改,逾期未交,未交
     */
    private String t_state = "未交";
    private String comment;
    private List<Data> homeworks;


    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public Integer getH_id() {
        return h_id;
    }

    public void setH_id(Integer h_id) {
        this.h_id = h_id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Long getCommit_time() {
        return commit_time;
    }

    public void setCommit_time(Long commit_time) {
        this.commit_time = commit_time;
    }

    public Integer getStudent_number() {
        return student_number;
    }

    public void setStudent_number(Integer student_number) {
        this.student_number = student_number;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getS_state() {
        return s_state;
    }

    public void setS_state(String s_state) {
        this.s_state = s_state;
    }

    public String getT_state() {
        return t_state;
    }

    public void setT_state(String t_state) {
        this.t_state = t_state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Data> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(List<Data> homeworks) {
        this.homeworks = homeworks;
    }
}
