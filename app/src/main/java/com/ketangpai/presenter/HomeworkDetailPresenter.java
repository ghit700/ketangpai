package com.ketangpai.presenter;

import android.content.Context;
import android.util.Log;

import com.ketangpai.base.BasePresenter;
import com.ketangpai.bean.Student_Homework;
import com.ketangpai.model.HomeworkModel;
import com.ketangpai.modelImpl.HomeworkModelImpl;
import com.ketangpai.viewInterface.HomeworkDetailViewInterface;

import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by nan on 2016/5/1.
 */
public class HomeworkDetailPresenter extends BasePresenter<HomeworkDetailViewInterface> {
    private HomeworkDetailViewInterface mTHomeworkViewInterface;
    private HomeworkModel mHomeworkModel;

    public HomeworkDetailPresenter() {
        mHomeworkModel = new HomeworkModelImpl();
    }

    public void correctHomework(Context context, Student_Homework homework) {
        if (isViewAttached()) {
            mTHomeworkViewInterface = getView();
            mHomeworkModel.correctHomework(context, homework, new UpdateListener() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onFailure(int i, String s) {
                    Log.i("====", "correctHomework  " + s);
                }
            });
        }
    }

    ;
}
