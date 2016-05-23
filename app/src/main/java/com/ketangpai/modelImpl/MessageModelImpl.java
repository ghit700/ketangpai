package com.ketangpai.modelImpl;

import android.content.Context;
import android.database.Cursor;

import com.ketangpai.bean.MessageInfo;
import com.ketangpai.bean.NewestMessage;
import com.ketangpai.callback.ResultCallback;
import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.constant.Constant;
import com.ketangpai.model.MessageModel;
import com.ketangpai.utils.DBUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by nan on 2016/5/2.
 */
public class MessageModelImpl implements MessageModel {

    DBUtils mDBUtils;

    public MessageModelImpl() {
        mDBUtils = new DBUtils();
    }

    @Override
    public void sendMessage(final Context context, final MessageInfo messageInfo, final String path, final ResultCallback resultCallback) {
        messageInfo.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                resultCallback.onSuccess(messageInfo);
                String sql = "select * from NewestMessage where receive_account in ('" + messageInfo.getReceive_account() + "','" + messageInfo.getSend_account() + "') " +
                        "and send_account  in ('" + messageInfo.getReceive_account() + "','" + messageInfo.getSend_account() + "')";
                BmobQuery<NewestMessage> query = new BmobQuery<NewestMessage>();
                query.doSQLQuery(context, sql, new SQLQueryListener<NewestMessage>() {
                    @Override
                    public void done(BmobQueryResult<NewestMessage> bmobQueryResult, BmobException e) {
                        List<NewestMessage> newestMessages = bmobQueryResult.getResults();
                        if (null != newestMessages && newestMessages.size() > 0) {
                            NewestMessage newestMessage = newestMessages.get(0);
                            newestMessage.setReceive_path(path);
                            newestMessage.updateMessageinfo(messageInfo);
                            newestMessage.update(context);
                        } else {
                            NewestMessage newestMessage = new NewestMessage();
                            newestMessage.setReceive_path(path);
                            newestMessage.updateMessageinfo(messageInfo);
                            newestMessage.save(context);
                        }
                    }
                });
            }

            @Override
            public void onFailure(int i, String s) {
                resultCallback.onFailure(s);
            }
        });
    }

    @Override
    public void getChatRecondList(Context context, String account, String send_account, final ResultsCallback resultsCallback) {
        String sql = "select * from MessageInfo where receive_account in ('" + account + "','" + send_account + "') " +
                "and send_account  in ('" + account + "','" + send_account + "')";
        BmobQuery<MessageInfo> bmobQuery = new BmobQuery<>();
        bmobQuery.doSQLQuery(context, sql, new SQLQueryListener<MessageInfo>() {
            @Override
            public void done(BmobQueryResult<MessageInfo> bmobQueryResult, BmobException e) {
                List<MessageInfo> messageInfos = bmobQueryResult.getResults();
                if (null != messageInfos && messageInfos.size() > 0) {
                    resultsCallback.onSuccess(messageInfos);
                }
            }
        });


    }

    @Override
    public void getNewestMessageList(Context context, String account, final ResultsCallback resultsCallback) {

        String sql = "select * from  NewestMessage where send_account=? or receive_account=?";
        BmobQuery<NewestMessage> query = new BmobQuery<>();
        query.doSQLQuery(context, sql, new SQLQueryListener<NewestMessage>() {
            @Override
            public void done(BmobQueryResult<NewestMessage> bmobQueryResult, BmobException e) {
                List<NewestMessage> newestMessages = bmobQueryResult.getResults();
                if (null != newestMessages) {
                    resultsCallback.onSuccess(newestMessages);
                }

            }
        }, account, account);

    }

    @Override
    public void saveNewestMessageList(List<NewestMessage> newestMessages) {
        String sql = "delete from newestmessage where time > 0";
        mDBUtils.delete(sql, null);
        sql = "insert into newestmessage (time,content,send_name,send_account," +
                "send_path,receive_name,receive_account,receive_paths) values(?,?,?,?,?,?,?,?)";
        Object[] bindArgs;
        bindArgs = new Object[8];
        for (NewestMessage message : newestMessages) {
            bindArgs[0] = message.getTime();
            bindArgs[1] = message.getContent();
            bindArgs[2] = message.getSend_name();
            bindArgs[3] = message.getSend_account();
            bindArgs[4] = message.getSend_path();
            bindArgs[5] = message.getReceive_name();
            bindArgs[6] = message.getReceive_account();
            bindArgs[7] = message.getReceive_path();
            mDBUtils.insert(sql, bindArgs);
        }
    }

    @Override
    public List<NewestMessage> loadNewestMessageListFromDB() {
        List<NewestMessage> newestMessages = new ArrayList<>();
        String sql;

        sql = "select time,content,send_name,send_account " +
                ",send_path,receive_name,receive_account,receive_paths from newestmessage";
        Cursor cursor = mDBUtils.select(sql, null);
        while (cursor.moveToNext()) {
            NewestMessage message = new NewestMessage();
            message.setTime(cursor.getLong(0));
            message.setContent(cursor.getString(1));
            message.setSend_name(cursor.getString(2));
            message.setSend_account(cursor.getString(3));
            String[] splitePaths = cursor.getString(4).split("/");
            message.setSend_path(Constant.ALBUM_PATH + Constant.DATA_FOLDER + splitePaths[splitePaths.length - 1]);
            message.setReceive_name(cursor.getString(5));
            message.setReceive_account(cursor.getString(6));
            splitePaths = cursor.getString(7).split("/");
            message.setReceive_path(Constant.ALBUM_PATH + Constant.DATA_FOLDER + splitePaths[splitePaths.length - 1]);
            newestMessages.add(message);
        }
        cursor.close();

        return newestMessages;
    }

    @Override
    public void saveChatRecordList(List<MessageInfo> messageInfos) {
        String sql = "delete from messageinfo where time > 0";
        if (messageInfos.size() > 1) {
            mDBUtils.delete(sql, null);
        }
        sql = "insert into messageinfo (time,content,send_name,send_account," +
                "send_path,receive_name,receive_account) values(?,?,?,?,?,?,?)";
        Object[] bindArgs;
        bindArgs = new Object[8];
        for (MessageInfo message : messageInfos) {
            bindArgs[0] = message.getTime();
            bindArgs[1] = message.getContent();
            bindArgs[2] = message.getSend_name();
            bindArgs[3] = message.getSend_account();
            bindArgs[4] = message.getSend_path();
            bindArgs[5] = message.getReceive_name();
            bindArgs[6] = message.getReceive_account();
            mDBUtils.insert(sql, bindArgs);
        }
    }

    @Override
    public List<MessageInfo> loadChatRecordListFromDB(String account, String send_account) {
        List<MessageInfo> messageInfos = new ArrayList<>();
        String sql;

        sql = "select time,content,send_name,send_account " +
                ",send_path,receive_name,receive_account from messageinfo where receive_account= ? or receive_account=? or " +
                "send_account=? or send_account=?";
        String[] selectionArgs = new String[]{account, send_account, account, send_account};
        Cursor cursor = mDBUtils.select(sql, selectionArgs);
        while (cursor.moveToNext()) {
            NewestMessage message = new NewestMessage();
            message.setTime(cursor.getLong(0));
            message.setContent(cursor.getString(1));
            message.setSend_name(cursor.getString(2));
            message.setSend_account(cursor.getString(3));
            String[] splitePaths = cursor.getString(4).split("/");
            message.setSend_path(Constant.ALBUM_PATH + Constant.DATA_FOLDER + splitePaths[splitePaths.length - 1]);
            message.setReceive_name(cursor.getString(5));
            message.setReceive_account(cursor.getString(6));
            messageInfos.add(message);
        }
        cursor.close();

        return messageInfos;
    }
}
