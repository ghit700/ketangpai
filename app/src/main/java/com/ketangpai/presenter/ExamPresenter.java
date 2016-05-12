package com.ketangpai.presenter;

import android.content.Context;

import com.ketangpai.base.BasePresenter;
import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.model.ExamModel;
import com.ketangpai.modelImpl.ExamModelImpl;
import com.ketangpai.viewInterface.ExamViewInterface;

import java.util.List;

import cn.bmob.v3.exception.BmobException;

/**
 * Created by nan on 2016/5/8.
 */
public class ExamPresenter extends BasePresenter<ExamViewInterface> {

    ExamViewInterface mExamViewInterface;
    ExamModel mExamModel;


    public ExamPresenter() {
        mExamModel = new ExamModelImpl();
    }

    public void getStudentReplykList(Context context, int t_id) {
        if (isViewAttached()) {
            mExamViewInterface = getView();
            mExamModel.getStudentExamList(context, t_id, new ResultsCallback() {
                @Override
                public void onSuccess(List list) {
                    mExamViewInterface.getStudentReplyListOnComplete(list);
                }

                @Override
                public void onFailure(BmobException e) {

                }
            });


        }
    }
}
