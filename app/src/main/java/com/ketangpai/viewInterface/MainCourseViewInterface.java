package com.ketangpai.viewInterface;

import com.ketangpai.bean.Course;
import com.ketangpai.bean.Student_Course;
import com.ketangpai.bean.Teacher_Course;

import java.util.List;

/**
 * Created by Administrator on 2016/4/19.
 */
public interface MainCourseViewInterface {
    /**
     * 获取课程列表
     *
     * @param courses
     */
    void getCourseListOnComplete(List<Course> courses);

    /**
     * 创建课程
     *
     * @param course
     */
    void createCourseOnComplete(Teacher_Course course);



    void showLoading();

    void hideLoading();
}
