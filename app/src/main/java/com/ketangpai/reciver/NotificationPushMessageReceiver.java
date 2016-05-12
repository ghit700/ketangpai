package com.ketangpai.reciver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ketangpai.activity.ChatActivity;
import com.ketangpai.activity.MainActivity;
import com.ketangpai.bean.MessageInfo;
import com.ketangpai.bean.PushMessage;
import com.ketangpai.bean.User;
import com.ketangpai.event.ReceiveMessageEvent;
import com.ketangpai.nan.ketangpai.R;

import org.greenrobot.eventbus.EventBus;

import cn.bmob.push.PushConstants;

/**
 * Created by nan on 2016/4/25.
 */
public class NotificationPushMessageReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
            Log.d("========", "客户端收到推送内容：" + intent.getStringExtra("msg"));
            String result = intent.getStringExtra("msg");
            JSONObject object = JSON.parseObject(result);
            PushMessage pushMessage = JSON.parseObject(object.getString("alert"), PushMessage.class);
            switch (pushMessage.getType()) {

                case 1:
                    MessageInfo messageInfo = JSON.parseObject(pushMessage.getObject(), MessageInfo.class);
                    ReceiveMessageEvent receiveMessageEvent = new ReceiveMessageEvent(messageInfo);
                    EventBus.getDefault().post(receiveMessageEvent);
                    User user = new User();
                    user.setAccount(messageInfo.getSend_account());
                    user.setName(messageInfo.getSend_name());
                    user.setPath(messageInfo.getSend_path());

                    Intent intent1 = new Intent(context, ChatActivity.class); //点击通知跳转
                    intent1.putExtra("send_user", user);
                    PendingIntent pintent = PendingIntent.getActivity(context, 0, intent1, 0);
                    Notification.Builder builder = new Notification.Builder(context);
                    builder.setSmallIcon(R.mipmap.ic_launcher);//设置图标
                    builder.setTicker(messageInfo.getContent());//手机状态栏的提示；
                    builder.setWhen(System.currentTimeMillis());//设置时间
                    builder.setContentTitle(messageInfo.getSend_name());//设置标题
                    builder.setContentText(messageInfo.getContent());//设置通知内容
                    builder.setContentIntent(pintent);//点击后的意图
                    builder.setDefaults(Notification.DEFAULT_SOUND);//设置提示声音
                    builder.setDefaults(Notification.DEFAULT_LIGHTS);//设置指示灯
                    builder.setDefaults(Notification.DEFAULT_VIBRATE);//设置震动
                    Notification notification = builder.build();
                    NotificationManager manager =
                            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    manager.notify(0, notification);
                    break;

                default:
                    break;
            }
        }
    }
}
