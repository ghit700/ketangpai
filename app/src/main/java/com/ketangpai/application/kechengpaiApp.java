package com.ketangpai.application;

import android.app.Application;

import com.ketangpai.utils.AppContextUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;


/**
 * Created by nan on 2016/3/10.
 */
public class kechengpaiApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppContextUtils.initContext(this);
        Bmob.initialize(this, "9d323483ab61f93c20e553ba404ee1a7");

    }
}
