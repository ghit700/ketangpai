package com.ketangpai.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by nan on 2016/4/9.
 */
public class Notification extends BmobObject {
    private List<NotificationInfo> notificationInfos;

    private String time;

    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<NotificationInfo> getNotificationInfos() {
        return notificationInfos;
    }

    public void setNotificationInfos(List<NotificationInfo> notificationInfos) {
        this.notificationInfos = notificationInfos;
    }

    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public int getCount() {
        return notificationInfos == null ? 1 : 1 + notificationInfos.size();
    }


}
