package com.ketangpai.presenter;

import android.content.Context;

import com.ketangpai.base.BasePresenter;
import com.ketangpai.bean.User_Group;
import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.model.UserModel;
import com.ketangpai.modelImpl.UserModelImpl;
import com.ketangpai.viewInterface.ContactsViewInterface;

import java.util.List;

import cn.bmob.v3.exception.BmobException;

/**
 * Created by nan on 2016/5/2.
 */
public class ContactsPresenter extends BasePresenter<ContactsViewInterface> {
    ContactsViewInterface mContactsViewInterface;
    UserModel userModel;

    public ContactsPresenter() {
        this.userModel = new UserModelImpl();
    }


    public void getConstactList(Context context, String account) {
        if (isViewAttached()) {
            mContactsViewInterface = getView();
            userModel.getUserGroup(context, account, new ResultsCallback() {
                @Override
                public void onSuccess(List list) {
                    userModel.saveUserGroup(list);
                    mContactsViewInterface.getContactListOnComplete(list);
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
