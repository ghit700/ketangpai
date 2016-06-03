package com.ketangpai.model;

import android.content.Context;

import com.ketangpai.bean.User_Group;
import com.ketangpai.callback.ResultCallback;
import com.ketangpai.bean.User;
import com.ketangpai.callback.ResultsCallback;

import java.io.File;
import java.util.List;

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

    public void getUserGroup(Context context, String account, ResultsCallback resultsCallback);

    void saveUser(User user);

    void searchUser(String account, ResultCallback resultCallback);

    void saveUserGroup(List<User_Group> user_groups);

    List<User_Group> loadUserGroupFromDB();

//    void getStudentList(Context mContext, int c_id,String account, ResultsCallback resultsCallback);
}
