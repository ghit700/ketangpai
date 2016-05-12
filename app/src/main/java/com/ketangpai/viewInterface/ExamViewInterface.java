package com.ketangpai.viewInterface;

import com.ketangpai.bean.Student_Homework;
import com.ketangpai.bean.Student_Reply;

import java.util.List;

/**
 * Created by nan on 2016/5/8.
 */
public interface ExamViewInterface {
    void getStudentReplyListOnComplete(List<Student_Reply> student_replies);
}
