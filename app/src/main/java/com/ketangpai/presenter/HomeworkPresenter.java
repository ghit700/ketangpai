package com.ketangpai.presenter;

import android.content.Context;

import com.ketangpai.base.BasePresenter;
import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.model.HomeworkModel;
import com.ketangpai.modelImpl.HomeworkModelImpl;
import com.ketangpai.viewInterface.HomeworkViewInterface;

import java.util.List;

import cn.bmob.v3.exception.BmobException;

/**
 * Created by nan on 2016/5/1.
 */
public class HomeworkPresenter extends BasePresenter<HomeworkViewInterface> {
    HomeworkViewInterface mTHomeworkViewInterface;
    HomeworkModel mHomeworkModel = new HomeworkModelImpl();


    public HomeworkPresenter() {
        mHomeworkModel = new HomeworkModelImpl();
    }

    public void getStudentHomeworkList(Context context, int h_id) {
        if (isViewAttached()) {
            mTHomeworkViewInterface = getView();
            mHomeworkModel.getStudentHomeworkList(context, h_id, new ResultsCallback() {
                @Override
                public void onSuccess(List list) {
                    mTHomeworkViewInterface.getStudentHomeworkListOnComplete(list);
                }

                @Override
                public void onFailure(BmobException e) {

                }
            });
        }
    }
}
