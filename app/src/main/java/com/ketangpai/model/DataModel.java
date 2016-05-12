package com.ketangpai.model;

import android.content.Context;

import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.callback.UploadFileResultCallback;
import com.ketangpai.bean.Data;

/**
 * Created by nan on 2016/4/24.
 */
public interface DataModel {
    void uploadData(Context context, Data data, UploadFileResultCallback resultCallBack);

    void getDataList(Context context, int c_id, ResultsCallback resultsCallback);
}
