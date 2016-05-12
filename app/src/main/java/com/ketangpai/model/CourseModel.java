package com.ketangpai.model;

import android.content.Context;

import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.callback.ResultCallback;
import com.ketangpai.bean.Teacher_Course;

/**
 * Created by Administrator on 2016/4/19.
 */
public interface CourseModel {
    void queryCourseList(Context context, String account, ResultsCallback resultsCallback);

    void createCourse(Context context, Teacher_Course course, String path, ResultCallback resultCallback);

    void deleteCourse(Context context, Integer c_id);
}
