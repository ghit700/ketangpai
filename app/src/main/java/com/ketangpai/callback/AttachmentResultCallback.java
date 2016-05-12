package com.ketangpai.callback;

import cn.bmob.v3.datatype.BmobFile;

/**
 * 返回的是对象
 * Created by nan on 2016/4/21.
 */
public interface AttachmentResultCallback {
    void onSuccess(BmobFile bmobFile);

    void onProgress(Integer value);

    void onFailure(String e);
}
