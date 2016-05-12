package com.ketangpai.presenter;

import android.content.Context;
import android.util.Log;

import com.ketangpai.base.BasePresenter;
import com.ketangpai.bean.User;
import com.ketangpai.fragment.AccountFragment;
import com.ketangpai.model.UserModel;
import com.ketangpai.modelImpl.UserModelImpl;
import com.ketangpai.viewInterface.AccountViewInterface;

import java.io.File;

import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by nan on 2016/4/23.
 */
public class AccountPresenter extends BasePresenter<AccountViewInterface> {
    AccountViewInterface accountViewInterface;
    UserModel userModel;

    public AccountPresenter() {
        userModel = new UserModelImpl();
    }

    public void uploadUserLogo(Context context, File file, User user) {
        if (isViewAttached()) {
            accountViewInterface = getView();
            accountViewInterface.showUploadLogoDialong();
            userModel.uploadUserLogo(context, file, user, new UpdateListener() {
                @Override
                public void onSuccess() {
                    accountViewInterface.uploadLogoOnComplete(1);
                    accountViewInterface.hideUploadLogoDialong();
                }

                @Override
                public void onFailure(int i, String s) {
                    Log.i(AccountFragment.TAG,s);
                    accountViewInterface.uploadLogoOnComplete(-1);

                }
            });
        } else {
            Log.i(AccountFragment.TAG, "uploadUserLogo 没有连接到view");
        }
    }
}
