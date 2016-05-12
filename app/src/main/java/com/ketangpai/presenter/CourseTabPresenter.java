package com.ketangpai.presenter;

import android.content.Context;
import android.util.Log;

import com.ketangpai.bean.Student_Course;
import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.callback.UploadFileResultCallback;
import com.ketangpai.base.BasePresenter;
import com.ketangpai.bean.Data;
import com.ketangpai.fragment.CourseTabFragment;
import com.ketangpai.model.DataModel;
import com.ketangpai.model.ExamModel;
import com.ketangpai.model.HomeworkModel;
import com.ketangpai.model.NoticeModel;
import com.ketangpai.model.NotificationModel;
import com.ketangpai.modelImpl.DataModelImpl;
import com.ketangpai.modelImpl.ExamModelImpl;
import com.ketangpai.modelImpl.HomeworkModelImpl;
import com.ketangpai.modelImpl.NoticeModelImpl;
import com.ketangpai.modelImpl.NotificationModelImpl;
import com.ketangpai.viewInterface.CourseTabViewInterface;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.GetListener;

/**
 * Created by nan on 2016/4/24.
 */
public class CourseTabPresenter extends BasePresenter<CourseTabViewInterface> {

    CourseTabViewInterface courseTabViewInterface;
    NoticeModel noticeModel;
    DataModel dataModel;
    HomeworkModel homeworkModel;
    NotificationModel notificationModel;
    ExamModel examModel;

    public CourseTabPresenter() {
        homeworkModel = new HomeworkModelImpl();
        dataModel = new DataModelImpl();
        noticeModel = new NoticeModelImpl();
        notificationModel = new NotificationModelImpl();
        examModel = new ExamModelImpl();
    }

    public void getNoticeList(Context context, int c_id) {
        if (isViewAttached()) {
            courseTabViewInterface = getView();
            noticeModel.getNoticeList(context, c_id, new ResultsCallback() {
                @Override
                public void onSuccess(List list) {
                    courseTabViewInterface.getNoticeListOnComplete(list);
                }

                @Override
                public void onFailure(BmobException e) {

                }
            });
        }
    }


    public void uploadData(final Context context, final Data data, final int c_id, final String c_name) {
        if (isViewAttached()) {
            courseTabViewInterface = getView();
            dataModel.uploadData(context, data, new UploadFileResultCallback() {
                @Override
                public void onSuccess(String url) {
                    BmobQuery<Data> query = new BmobQuery<Data>();
                    query.getObject(context, data.getObjectId(), new GetListener<Data>() {
                        @Override
                        public void onSuccess(Data data1) {
                            notificationModel.publishStudentNofication(context, data1, c_id, c_name);
                        }

                        @Override
                        public void onFailure(int i, String s) {

                        }
                    });
                    courseTabViewInterface.uploadDataOnComplete(url);
                }

                @Override
                public void onProgress(int value) {
                    courseTabViewInterface.uploadOnProgress(value);
                }

                @Override
                public void onFailure(String e) {
                    Log.i(CourseTabFragment.TAG, "uploadData " + e);
                }
            });
        }
    }

    public void getDataList(Context context, int c_id) {
        if (isViewAttached()) {
            courseTabViewInterface = getView();
            dataModel.getDataList(context, c_id, new ResultsCallback() {
                @Override
                public void onSuccess(List list) {
                    courseTabViewInterface.getDataListOnComplete(list);
                }

                @Override
                public void onFailure(BmobException e) {
                    Log.i(CourseTabFragment.TAG, "getDataList " + e);
                }
            });
        }
    }

    public void getHomeworkList(Context context, int c_id) {
        if (isViewAttached()) {
            courseTabViewInterface = getView();
            homeworkModel.getHomeworkList(context, c_id, new ResultsCallback() {
                @Override
                public void onSuccess(List list) {
                    courseTabViewInterface.getHomeworkListOnComplete(list);
                }

                @Override
                public void onFailure(BmobException e) {
                    Log.i(CourseTabFragment.TAG, "getHomeworkList " + e);
                }
            });
        }
    }


    public void getExamList(Context context, int c_id) {
        if (isViewAttached()) {
            courseTabViewInterface = getView();
            examModel.getExamList(context, c_id, new ResultsCallback() {
                @Override
                public void onSuccess(List list) {
                    courseTabViewInterface.getExamkListOnComplete(list);
                }

                @Override
                public void onFailure(BmobException e) {
                    Log.i(CourseTabFragment.TAG, "getExamList " + e);
                }
            });
        }
    }



}
