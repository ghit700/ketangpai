package com.ketangpai.callback;

/**
 * Created by nan on 2016/4/24.
 */
public interface UploadFileResultCallback {
    void onSuccess(String url);

    void onProgress(int value);

    void onFailure(String e);
}
