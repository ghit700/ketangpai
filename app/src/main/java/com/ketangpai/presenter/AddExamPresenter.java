package com.ketangpai.presenter;

import android.content.Context;
import android.util.Log;

import com.ketangpai.base.BasePresenter;
import com.ketangpai.bean.Data;
import com.ketangpai.bean.Teacher_Homework;
import com.ketangpai.bean.Test;
import com.ketangpai.callback.AttachmentResultCallback;
import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.fragment.AddExamFragment;
import com.ketangpai.fragment.AddHomeworkFragment;
import com.ketangpai.fragment.AddNoticeFragment;
import com.ketangpai.model.ExamModel;
import com.ketangpai.model.FileModel;
import com.ketangpai.model.HomeworkModel;
import com.ketangpai.model.NotificationModel;
import com.ketangpai.modelImpl.ExamModelImpl;
import com.ketangpai.modelImpl.FileModelImpl;
import com.ketangpai.modelImpl.HomeworkModelImpl;
import com.ketangpai.modelImpl.NotificationModelImpl;
import com.ketangpai.utils.FileUtils;
import com.ketangpai.viewInterface.AddExamViewInterface;
import com.ketangpai.viewInterface.AddHomeworkViewInterface;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by nan on 2016/4/24.
 */
public class AddExamPresenter extends BasePresenter<AddExamViewInterface> {
    private AddExamViewInterface mAddExamViewInterface;
    private ExamModel examModel;
    private NotificationModel notificationModel;

    public AddExamPresenter() {
        examModel = new ExamModelImpl();
        notificationModel = new NotificationModelImpl();
    }

    public void publishExam(final Context context, final Test test, final int c_id, final String c_name) {
        if (isViewAttached()) {
            mAddExamViewInterface = getView();
            examModel.publishExam(context, test, new SaveListener() {
                @Override
                public void onSuccess() {
                    BmobQuery<Test> query = new BmobQuery<Test>();
                    query.getObject(context, test.getObjectId(), new GetListener<Test>() {
                        @Override
                        public void onSuccess(Test test1) {
                            mAddExamViewInterface.addExamOnComplete(test1);
                            examModel.createStudentExamList(context, test1);
                            notificationModel.publishStudentNofication(context, test1, c_id, c_name);
                        }

                        @Override
                        public void onFailure(int i, String s) {

                        }
                    });

                }

                @Override
                public void onFailure(int i, String s) {
                    Log.i(AddHomeworkFragment.TAG, s);
                }
            });
        } else {
            Log.i(AddExamFragment.TAG, "publishExam 没有连接到view");
        }
    }

    public void loadChooseSubjects(Context context,int type,String text){
        if (isViewAttached()){
            mAddExamViewInterface=getView();
            examModel.loadChooseSubjects(context, type, text,new ResultsCallback() {
                @Override
                public void onSuccess(List list) {
                    mAddExamViewInterface.loadChooseSubjectsOnComplete(list);
                }

                @Override
                public void onFailure(BmobException e) {

                }
            });
        }
    }

}
