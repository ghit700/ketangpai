package com.ketangpai.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by nan on 2016/5/5.
 */
public class Student_Reply extends BmobObject {
    private Integer c_id;
    private Integer t_id;
    private String account;
    private String student_name;
    private Integer student_number;
    private String s_state;
    private String t_state;
    private Long commit_time;
    private Integer grade;
    private List<Subject> subjects;

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public Integer getT_id() {
        return t_id;
    }

    public void setT_id(Integer t_id) {
        this.t_id = t_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public Integer getStudent_number() {
        return student_number;
    }

    public void setStudent_number(Integer student_number) {
        this.student_number = student_number;
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

    public Long getCommit_time() {
        return commit_time;
    }

    public void setCommit_time(Long commit_time) {
        this.commit_time = commit_time;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
