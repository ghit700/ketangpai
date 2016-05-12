package com.ketangpai.model;

import android.content.Context;

import com.ketangpai.callback.ResultCallback;
import com.ketangpai.bean.User;
import com.ketangpai.callback.ResultsCallback;

import java.io.File;

import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by nan on 2016/4/16.
 */
public interface UserModel {
    public void login(Context context, String account, String password, ResultCallback ResultCallback);

    public void register(Context context, User user, SaveListener resultCallback);

    public void updateUserInfo(Context context, String u_id, String columnName, String columnValue, UpdateListener resultCallback);

    public void uploadUserLogo(Context context, File file, User user, UpdateListener resultCallback);

    public void getUserGroup(Context context,  String account, ResultsCallback resultsCallback);
}
