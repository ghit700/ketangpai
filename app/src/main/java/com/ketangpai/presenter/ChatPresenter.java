package com.ketangpai.presenter;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.ketangpai.base.BasePresenter;
import com.ketangpai.bean.MessageInfo;
import com.ketangpai.bean.PushMessage;
import com.ketangpai.callback.ResultCallback;
import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.fragment.ChatFragment;
import com.ketangpai.model.MessageModel;
import com.ketangpai.model.NotificationModel;
import com.ketangpai.modelImpl.MessageModelImpl;
import com.ketangpai.modelImpl.NotificationModelImpl;
import com.ketangpai.utils.PushManager;
import com.ketangpai.viewInterface.ChatViewInterface;

import java.util.List;

import cn.bmob.v3.exception.BmobException;

/**
 * Created by nan on 2016/5/3.
 */
public class ChatPresenter extends BasePresenter<ChatViewInterface> {
    ChatViewInterface mChatViewInterface;
    MessageModel mMessageModel;

    public ChatPresenter() {
        mMessageModel = new MessageModelImpl();
    }

    public void getChatRecondList(Context context, String account, String send_account) {
        if (isViewAttached()) {
            mChatViewInterface = getView();
            mMessageModel.getChatRecondList(context, account, send_account, new ResultsCallback() {
                @Override
                public void onSuccess(List list) {
                    mChatViewInterface.getChatRecondListOnComplete(list);
                }

                @Override
                public void onFailure(BmobException e) {

                }
            });
        }
    }

    ;

    public void sendMessage(final Context context, MessageInfo messageInfo,String path) {
        if (isViewAttached()) {
            mChatViewInterface = getView();
            mMessageModel.sendMessage(context, messageInfo,path ,new ResultCallback() {
                @Override
                public void onSuccess(Object object) {

                    PushManager.sendMessage(context, (MessageInfo) object);
                }

                @Override
                public void onFailure(String e) {
                    Log.i(ChatFragment.TAG, "sendMessage" + e);
                }
            });

        }
    }

    ;
}
