package com.ketangpai.model;

import android.content.Context;

import com.ketangpai.bean.MessageInfo;
import com.ketangpai.bean.NewestMessage;
import com.ketangpai.callback.ResultCallback;
import com.ketangpai.callback.ResultsCallback;

import java.util.List;

/**
 * Created by nan on 2016/5/2.
 */
public interface MessageModel {
    void sendMessage(Context context, MessageInfo messageInfo, String path, ResultCallback resultCallback);

    void getChatRecondList(Context context, String account, String send_account, ResultsCallback resultsCallback);

    void getNewestMessageList(Context context, String account, ResultsCallback resultsCallback);

    void saveNewestMessageList(List<NewestMessage> newestMessages);

    List<NewestMessage> loadNewestMessageListFromDB();

    void saveChatRecordList(List<MessageInfo> messageInfos);

    List<MessageInfo> loadChatRecordListFromDB(String account, String send_account);
}
