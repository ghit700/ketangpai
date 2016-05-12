package com.ketangpai.presenter;

import android.content.Context;
import android.util.Log;

import com.ketangpai.base.BasePresenter;
import com.ketangpai.bean.User;
import com.ketangpai.fragment.AccountUpdateFragment;
import com.ketangpai.model.UserModel;
import com.ketangpai.modelImpl.UserModelImpl;
import com.ketangpai.viewInterface.AccountUpdateViewInterface;

import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2016/4/18.
 */
public class AccountUpdatePresenter extends BasePresenter<AccountUpdateViewInterface> {
    UserModel userModel;
    AccountUpdateViewInterface accountViewInterface;

    public AccountUpdatePresenter() {
        userModel = new UserModelImpl();
    }

    public void updateUserInfo(Context context, String u_id, final String columnName, String columnValue) {
        if (isViewAttached()) {
            accountViewInterface = getView();
            userModel.updateUserInfo(context, u_id, columnName, columnValue, new UpdateListener() {
                @Override
                public void onSuccess() {
                    accountViewInterface.updateUserInfoOnComplete(columnName);
                }

                @Override
                public void onFailure(int i, String s) {
                    Log.i(AccountUpdateFragment.TAG, "updateUserInfo " + i + "  s=");
                }
            });
        } else {
            Log.i(AccountUpdateFragment.TAG, "没有连接viwe");
        }
    }


}
