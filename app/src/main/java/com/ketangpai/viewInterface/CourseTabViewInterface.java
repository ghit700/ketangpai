package com.ketangpai.viewInterface;

import com.ketangpai.bean.Notice;
import com.ketangpai.bean.Teacher_Homework;

import java.util.List;

/**
 * Created by nan on 2016/4/24.
 */
public interface CourseTabViewInterface {
    void getHomeworkListOnComplete(List<Teacher_Homework> homeworks);

    void uploadOnProgress(int value);

    void uploadDataOnComplete(String url);

    void getDataListOnComplete(List datas);

    void getNoticeListOnComplete(List<Notice> notices);

    void getExamkListOnComplete(List exams);
}
