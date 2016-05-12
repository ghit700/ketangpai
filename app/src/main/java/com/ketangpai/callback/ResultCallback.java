package com.ketangpai.callback;

/**
 * 返回的是对象
 * Created by nan on 2016/4/21.
 */
public interface ResultCallback {
    void onSuccess(Object object);

    void onFailure(String e);
}
