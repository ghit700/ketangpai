package com.ketangpai.model;

import android.content.Context;

import com.ketangpai.bean.Student_Homework;
import com.ketangpai.bean.Student_Reply;
import com.ketangpai.bean.Teacher_Homework;
import com.ketangpai.bean.Test;
import com.ketangpai.callback.ResultCallback;
import com.ketangpai.callback.ResultsCallback;

import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by nan on 2016/5/5.
 */
public interface ExamModel {
    void publishExam(Context context, Test test, SaveListener resultCallback);

    void createStudentExamList(Context context, Test test);

    void getExamList(Context context, int c_id, ResultsCallback resultsCallback);

    void getStudentExamList(Context context, int t_id, ResultsCallback resultsCallback);


    void correctExam(Context context, Student_Reply student_reply, UpdateListener resultCallback);

    void getSearchExamList(Context context, String content, ResultsCallback resultsCallback);

    void deleteExam(Context context, int t_id);

    void loadChooseSubjects(Context context,int type,ResultsCallback resultsCallback);

}
