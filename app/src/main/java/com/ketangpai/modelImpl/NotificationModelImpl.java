package com.ketangpai.modelImpl;

import android.content.Context;
import android.util.Log;

import com.ketangpai.bean.Test;
import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.bean.Data;
import com.ketangpai.bean.Notice;
import com.ketangpai.bean.Notification;
import com.ketangpai.bean.NotificationInfo;
import com.ketangpai.bean.Teacher_Homework;
import com.ketangpai.model.NotificationModel;
import com.ketangpai.utils.PushManager;
import com.ketangpai.utils.TimeUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * Created by nan on 2016/4/25.
 */
public class NotificationModelImpl implements NotificationModel {
    @Override
    public void publishStudentNofication(Context context, Object object, int c_id, String c_name) {
        int type = 0, type_id = 0;
        String content = "";

        if (object instanceof Notice) {
            type = 1;
            type_id = ((Notice) object).getN_id();
            content = "发布了新公告~ " + ((Notice) object).getTitle();
        } else if (object instanceof Teacher_Homework) {
            type_id = ((Teacher_Homework) object).getH_id();
            type = 2;
            content = "发布了新作业~ " + ((Teacher_Homework) object).getTitle();

        } else if (object instanceof Data) {
            type_id = ((Data) object).getD_id();
            type = 3;
            content = "分享了新资料~ " + ((Data) object).getName();
        } else if (object instanceof Test) {
            type_id = ((Test) object).getT_id();
            type = 4;
            content = "发布了新测试~ " + ((Test) object).getTitle();
        }
        PushManager.publishStudentNotification(context, c_id, type_id, type, content, c_name);
    }

    public void saveNotificationToTable(final Context context, final String account, final NotificationInfo notificationInfo) {
        final String time = TimeUtils.getNotificationTime(System.currentTimeMillis());

        String sql = "select * from Notification where account=? and time=?";
        BmobQuery<Notification> query1 = new BmobQuery<Notification>();

        query1.doSQLQuery(context, sql, new SQLQueryListener<Notification>() {
            @Override
            public void done(BmobQueryResult<Notification> bmobQueryResult, BmobException e) {
                List<Notification> notifications = bmobQueryResult.getResults();
                if (null != notifications) {
                    if (notifications.size() > 0) {
                        Notification notification = notifications.get(0);
                        List<NotificationInfo> notificationInfos = notification.getNotificationInfos();
                        notification.addUnique("notificationInfos", notificationInfo);
                        notification.update(context);

                    } else {
                        Notification notification = new Notification();
                        notification.setTime(time);
                        notification.setAccount(account);
                        notification.addUnique("notificationInfos", notificationInfo);
                        notification.save(context);
                    }
                }
            }
        }, account, time);
    }

    @Override
    public void getNotificationList(Context context, String account, final ResultsCallback resultsCallback) {
        String sql = "select * from Notification where account=? order by time desc";
        BmobQuery<Notification> query = new BmobQuery<>();
        query.doSQLQuery(context, sql, new SQLQueryListener<Notification>() {
            @Override
            public void done(BmobQueryResult<Notification> bmobQueryResult, BmobException e) {
                List<Notification> notifications = bmobQueryResult.getResults();
                if (null != notifications) {
                    resultsCallback.onSuccess(notifications);
                }
            }
        }, account);
    }
}

