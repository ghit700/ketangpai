package com.ketangpai.utils;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.ketangpai.bean.Installation;
import com.ketangpai.bean.MessageInfo;
import com.ketangpai.bean.Notification;
import com.ketangpai.bean.NotificationInfo;
import com.ketangpai.bean.PushMessage;
import com.ketangpai.bean.Student_Course;
import com.ketangpai.model.NotificationModel;
import com.ketangpai.modelImpl.NotificationModelImpl;

import java.util.List;

import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by nan on 2016/4/25.
 */
public class PushManager {
    public static void publishStudentNotification(final Context context, int c_id, int type_id, int type, final String content, String c_name) {
        final BmobPushManager bmobPushManager = new BmobPushManager(context);
        String sql = "select account from Student_Course where c_id=?";
        final BmobQuery<Student_Course> query = new BmobQuery<Student_Course>();
        final NotificationInfo notificationInfo = new NotificationInfo();
        notificationInfo.setC_name(c_name);
        notificationInfo.setContent(content);
        notificationInfo.setType(type);
        notificationInfo.setType_id(type_id);
        query.doSQLQuery(context, sql, new SQLQueryListener<Student_Course>() {
            @Override
            public void done(BmobQueryResult<Student_Course> bmobQueryResult, BmobException e) {
                List<Student_Course> students = bmobQueryResult.getResults();
                if (null != students) {
                    for (final Student_Course student : students) {
                        Log.i("===", " account=" + student.getAccount() + "  " + JSON.toJSONString(notificationInfo));
                        NotificationModel notificationModel = new NotificationModelImpl();
                        notificationModel.saveNotificationToTable(context, student.getAccount(), notificationInfo);

                        BmobQuery<Installation> query = new BmobQuery<Installation>();
                        query.addWhereEqualTo("account", student.getAccount());
                        bmobPushManager.setQuery(query);
                        PushMessage pushMessage = new PushMessage();
                        pushMessage.setType(0);
                        pushMessage.setObject(JSON.toJSONString(notificationInfo));
                        bmobPushManager.pushMessage(JSON.toJSONString(pushMessage));
                    }


                } else {
                    Log.i("=======", "publishStudentNotification students null");
                }
            }


        }, c_id);


    }

    public static void sendMessage(Context context, MessageInfo messageInfo) {
        PushMessage pushMessage = new PushMessage();
        pushMessage.setType(1);
        pushMessage.setObject(JSON.toJSONString(messageInfo));
        BmobQuery<Installation> query = new BmobQuery<Installation>();
        query.addWhereEqualTo("account", messageInfo.getReceive_account());
        BmobPushManager bmobPushManager = new BmobPushManager(context);
        bmobPushManager.setQuery(query);
        bmobPushManager.pushMessage(JSON.toJSONString(pushMessage));
    }

    ;


//    public static void publishTeacherNotification(Context context,int c_id,)


}
