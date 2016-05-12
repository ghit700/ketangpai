package com.ketangpai.viewInterface;

import com.ketangpai.bean.Data;
import com.ketangpai.bean.Subject;
import com.ketangpai.bean.Teacher_Homework;
import com.ketangpai.bean.Test;

import java.util.List;

/**
 * Created by nan on 2016/4/22.
 */
public interface AddExamViewInterface {
    void addExamOnComplete(Test test);
    void loadChooseSubjectsOnComplete(List<Subject> subjects);
}
