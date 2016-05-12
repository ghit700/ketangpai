package com.ketangpai.bean;

/**
 * Created by nan on 2016/4/21.
 */
public class Teacher_Course extends Course {
    private Integer numbers;
    private  String t_name;

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }
}
