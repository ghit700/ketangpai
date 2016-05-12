package com.ketangpai.bean;

/**
 * Created by nan on 2016/4/21.
 */
public class Student_Course extends Course {

    private String teacher;
    private String student_name;
    private Integer student_number;
    private Long add_time;

    public String getStudent_name() {
        return student_name;
    }

    public Long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Long add_time) {
        this.add_time = add_time;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
