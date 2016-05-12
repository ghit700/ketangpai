package com.ketangpai.presenter;

import android.content.Context;

import com.ketangpai.base.BasePresenter;
import com.ketangpai.bean.Student_Reply;
import com.ketangpai.model.ExamModel;
import com.ketangpai.modelImpl.ExamModelImpl;
import com.ketangpai.viewInterface.ExamDetailViewInterface;

import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by nan on 2016/5/1.
 */
public class ExamDetailPresenter extends BasePresenter<ExamDetailViewInterface> {
    private ExamDetailViewInterface mExamDetailViewInterface;
    private ExamModel mExamModel;

    public ExamDetailPresenter() {
        mExamModel = new ExamModelImpl();
    }

    public void correctExam(Context context, Student_Reply student_reply) {
        if (isViewAttached()) {
            mExamDetailViewInterface = getView();

            mExamModel.correctExam(context, student_reply, new UpdateListener() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onFailure(int i, String s) {

                }
            });


        }
    }

    ;
}
