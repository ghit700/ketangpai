package com.ketangpai.modelImpl;

import android.content.Context;

import com.ketangpai.bean.MessageInfo;
import com.ketangpai.bean.NewestMessage;
import com.ketangpai.bean.PushMessage;
import com.ketangpai.callback.ResultCallback;
import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.model.MessageModel;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by nan on 2016/5/2.
 */
public class MessageModelImpl implements MessageModel {
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
//        query.addWhereEqualTo("receive_account", account);
//        query.findObjects(context, new FindListener<NewestMessage>() {
//            @Override
//            public void onSuccess(List<NewestMessage> list) {
//                resultsCallback.onSuccess(list);
//            }
//
//            @Override
//            public void onError(int i, String s) {
//
//            }
//        });
    }
}
