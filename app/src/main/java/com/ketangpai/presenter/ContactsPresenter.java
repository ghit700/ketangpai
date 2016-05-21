package com.ketangpai.presenter;

import android.content.Context;

import com.ketangpai.base.BasePresenter;
import com.ketangpai.bean.User_Group;
import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.model.FileModel;
import com.ketangpai.model.UserModel;
import com.ketangpai.modelImpl.FileModelImpl;
import com.ketangpai.modelImpl.UserModelImpl;
import com.ketangpai.viewInterface.ContactsViewInterface;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;

/**
 * Created by nan on 2016/5/2.
 */
public class ContactsPresenter extends BasePresenter<ContactsViewInterface> {
    ContactsViewInterface mContactsViewInterface;
    UserModel userModel;
    FileModel fileModel;

    public ContactsPresenter() {
        this.userModel = new UserModelImpl();
        fileModel = new FileModelImpl();
    }


    public void getConstactList(final Context context, String account) {
        if (isViewAttached()) {
            mContactsViewInterface = getView();
            userModel.getUserGroup(context, account, new ResultsCallback() {
                @Override
                public void onSuccess(List list) {

                    mContactsViewInterface.getContactListOnComplete(list);
                    for (User_Group u : (List<User_Group>) list) {
                        if (!u.getPath().equals("")) {
                            String[] splitePaths = u.getPath().split("/");

                            fileModel.downloadData(context, u.getPath(), splitePaths[splitePaths.length - 1], new DownloadFileListener() {
                                @Override
                                public void onSuccess(String s) {

                                }

                                @Override
                                public void onFailure(int i, String s) {

                                }
                            });

                        }
                    }
                    userModel.saveUserGroup(list);
                }

                @Override
                public void onFailure(BmobException e) {

                }
            });
        }
    }

    public void loadConstactListFromDB() {
        if (isViewAttached()) {
            mContactsViewInterface = getView();
            List<User_Group> user_groups = userModel.loadUserGroupFromDB();
            mContactsViewInterface.loadContactFromDB(user_groups);
        }
    }

    ;
}
