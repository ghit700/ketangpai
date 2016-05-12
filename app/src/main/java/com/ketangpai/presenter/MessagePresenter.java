package com.ketangpai.presenter;

import android.content.Context;

import com.ketangpai.base.BasePresenter;
import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.model.MessageModel;
import com.ketangpai.modelImpl.MessageModelImpl;
import com.ketangpai.viewInterface.MessageViewInterface;

import java.util.List;

import cn.bmob.v3.exception.BmobException;

/**
 * Created by nan on 2016/5/3.
 */
public class MessagePresenter extends BasePresenter<MessageViewInterface> {
    MessageViewInterface mMessageViewInterface;
    MessageModel mMessageModel;

    public MessagePresenter() {
        mMessageModel = new MessageModelImpl();
    }

    public void getNewestMessageLis(Context context, String account) {
        if (isViewAttached()) {
            mMessageViewInterface = getView();
            mMessageModel.getNewestMessageList(context, account, new ResultsCallback() {
                @Override
                public void onSuccess(List list) {
                    mMessageViewInterface.getNewestMessageListOnComplete(list);
                }

                @Override
                public void onFailure(BmobException e) {

                }
            });
        }
    }
}
