package com.ketangpai.model;

import android.content.Context;

import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.bean.Notice;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by nan on 2016/4/22.
 */
public interface NoticeModel {
    void publishNotice(Context context, Notice notice, SaveListener resultCallback);

    void getNoticeList(Context context, int c_id, ResultsCallback resultsCallback);


    void getSearchNoticeList(Context context, String content, ResultsCallback resultsCallback);

}
