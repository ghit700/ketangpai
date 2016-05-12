package com.ketangpai.presenter;

import android.content.Context;
import android.util.Log;

import com.ketangpai.base.BasePresenter;
import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.model.ExamModel;
import com.ketangpai.model.HomeworkModel;
import com.ketangpai.model.NoticeModel;
import com.ketangpai.modelImpl.ExamModelImpl;
import com.ketangpai.modelImpl.HomeworkModelImpl;
import com.ketangpai.modelImpl.NoticeModelImpl;
import com.ketangpai.viewInterface.SearchViewInterface;

import java.util.List;

import cn.bmob.v3.exception.BmobException;

/**
 * Created by nan on 2016/5/9.
 */
public class SearchPresenter extends BasePresenter<SearchViewInterface> {
    private SearchViewInterface mSearchViewInterface;
    private HomeworkModel mHomeworkModel;
    private NoticeModel mNoticeModel;
    private ExamModel mExamModel;

    public SearchPresenter() {
        mHomeworkModel = new HomeworkModelImpl();
        mNoticeModel = new NoticeModelImpl();
        mExamModel = new ExamModelImpl();
    }


    public void getSearchList(Context context, int type, String content) {
        if (isViewAttached()) {
            mSearchViewInterface = getView();
            switch (type) {
                case 0:
                    mHomeworkModel.getSearchHomeworkList(context, content, new ResultsCallback() {
                        @Override
                        public void onSuccess(List list) {
                            mSearchViewInterface.loadSearchListOnComplete(list);
                        }

                        @Override
                        public void onFailure(BmobException e) {
                            Log.i("=====getSearchList", e.getMessage());
                        }
                    });
                    break;
                case 1:
                    mNoticeModel.getSearchNoticeList(context, content, new ResultsCallback() {
                        @Override
                        public void onSuccess(List list) {
                            mSearchViewInterface.loadSearchListOnComplete(list);
                        }

                        @Override
                        public void onFailure(BmobException e) {
                            Log.i("=====getSearchList", e.getMessage());
                        }
                    });
                    break;
                case 2:
                    mExamModel.getSearchExamList(context, content, new ResultsCallback() {
                        @Override
                        public void onSuccess(List list) {
                            mSearchViewInterface.loadSearchListOnComplete(list);
                        }

                        @Override
                        public void onFailure(BmobException e) {
                            Log.i("=====getSearchList", e.getMessage());
                        }
                    });
                    break;

                default:
                    break;
            }
        }
    }
}
