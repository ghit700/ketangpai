package com.ketangpai.model;

import android.content.Context;

import com.ketangpai.bean.Student_Course;
import com.ketangpai.bean.Student_Homework;
import com.ketangpai.callback.ResultCallback;
import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.bean.Teacher_Homework;

import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by nan on 2016/4/24.
 */
public interface HomeworkModel {
    void publishHomework(Context context, Teacher_Homework homework, SaveListener resultCallback);

    void createStudentHomeworkList(Context context, Teacher_Homework homework);

    void getHomeworkList(Context context, int c_id, ResultsCallback resultsCallback);

    void getStudentHomeworkList(Context context, int h_id, ResultsCallback resultsCallback);


    void correctHomework(Context context, Student_Homework homework,UpdateListener resultCallback);

    void deleteHomework(Context context,int h_id);

    void getSearchHomeworkList(Context context,String content,ResultsCallback resultsCallback);
}
