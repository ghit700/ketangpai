package com.ketangpai.model;

import android.content.Context;

import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.bean.NotificationInfo;

/**
 * Created by nan on 2016/4/25.
 */
public interface NotificationModel {
    void publishStudentNofication(Context context, Object object, int c_id, String c_name);

    void saveNotificationToTable(Context context, String account, NotificationInfo notificationInfo);

    void getNotificationList(Context context, String account, ResultsCallback resultsCallback);
}
