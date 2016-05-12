package com.ketangpai.bean;

import android.content.Context;

import cn.bmob.v3.BmobInstallation;

/**
 * Created by nan on 2016/4/25.
 */
public class Installation extends BmobInstallation {
    public Installation(Context context) {
        super(context);
    }

    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
