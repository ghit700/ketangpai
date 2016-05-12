package com.ketangpai.modelImpl;

import android.content.Context;
import android.util.Log;

import com.ketangpai.bean.Search;
import com.ketangpai.bean.Teacher_Homework;
import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.bean.Notice;
import com.ketangpai.fragment.AddNoticeFragment;
import com.ketangpai.fragment.CourseTabFragment;
import com.ketangpai.model.NoticeModel;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by nan on 2016/4/22.
 */
public class NoticeModelImpl implements NoticeModel {


    @Override
    public void publishNotice(Context context, Notice notice, SaveListener resultCallback) {
        Log.i(AddNoticeFragment.TAG, "publishNotice c_id=" + notice.getC_id() + " title=" + notice.getTitle() +
                " content=" + notice.getContent());
        notice.save(context, resultCallback);
    }

    @Override
    public void getNoticeList(Context context, int c_id, final ResultsCallback resultsCallback) {
        Log.i(CourseTabFragment.TAG, "getNoticeList c_id=" + c_id);
        String sql = "select * from Notice where c_id=?";
        BmobQuery<Notice> bmobQuery = new BmobQuery<Notice>();
        bmobQuery.doSQLQuery(context, sql, new SQLQueryListener<Notice>() {
            @Override
            public void done(BmobQueryResult<Notice> bmobQueryResult, BmobException e) {
                List list = bmobQueryResult.getResults();
                if (null != list) {
                    resultsCallback.onSuccess(list);
                } else {
                    Log.i(CourseTabFragment.TAG, "getNoticeList null");

                }
            }
        }, c_id);

    }


    @Override
    public void getSearchNoticeList(Context context, String content, final ResultsCallback resultsCallback) {
        String sql = "select * from Notice where title like '%" + content + "%' or content like '%" + content + "%'";
        BmobQuery<Notice> query = new BmobQuery<>();
        query.doSQLQuery(context, sql, new SQLQueryListener<Notice>() {
            @Override
            public void done(BmobQueryResult<Notice> bmobQueryResult, BmobException e) {
                if (null != bmobQueryResult) {
                    List<Notice> notices = bmobQueryResult.getResults();
                    List<Search> searches = new ArrayList<Search>();
                    if (null != notices && notices.size() > 0) {
                        for (Notice notice : notices) {
                            Search search = new Search();
                            search.setType(2);
                            search.setType_id(notice.getN_id());
                            search.setTitle(notice.getTitle());
                            search.setContent(notice.getContent());
                            searches.add(search);
                        }
                        resultsCallback.onSuccess(searches);
                    } else {
                        resultsCallback.onSuccess(searches);
                    }
                } else {
                    resultsCallback.onFailure(e);
                }
            }
        });
    }
}
