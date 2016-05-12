package com.ketangpai.presenter;

import android.content.Context;
import android.util.Log;

import com.ketangpai.callback.ResultCallback;
import com.ketangpai.activity.LoginActivity;
import com.ketangpai.base.BasePresenter;
import com.ketangpai.bean.User;
import com.ketangpai.model.UserModel;
import com.ketangpai.modelImpl.UserModelImpl;
import com.ketangpai.viewInterface.LoginViewInterface;


import cn.bmob.v3.listener.SaveListener;

/**
 * Created by nan on 2016/4/16.
 */
public class LoginPresenter extends BasePresenter<LoginViewInterface> {
    LoginViewInterface loginViewInterface;
    UserModel userModel;


    public LoginPresenter() {
        userModel = new UserModelImpl();
    }

    public void login(final Context context, String account, String password) {
        if (isViewAttached()) {
            loginViewInterface = getView();
            userModel.login(context, account, password, new ResultCallback() {
                @Override
                public void onSuccess(Object object) {

                    loginViewInterface.login((User) object);
                }

                @Override
                public void onFailure(String e) {
                    Log.i(LoginActivity.TAG, "login  " + e);
                }
            });
        } else {
            Log.i(LoginActivity.TAG, "没有连接view");
            return;
        }

        loginViewInterface.showLoginLoading();


    }

    public void register(Context context, User user) {
        if (isViewAttached()) {
            loginViewInterface = getView();

            loginViewInterface.showRegisterLoading();

            userModel.register(context, user, new SaveListener() {
                @Override
                public void onSuccess() {
                    loginViewInterface.register(1);
                    loginViewInterface.hideRegisterLoading();
                }

                @Override
                public void onFailure(int i, String s) {
                    Log.i(LoginActivity.TAG, "register " + s);
                    loginViewInterface.register(-1);
                    loginViewInterface.hideRegisterLoading();

                }
            });

        } else {
            Log.i(LoginActivity.TAG, "没有连接view");
            return;
        }


    }
}
