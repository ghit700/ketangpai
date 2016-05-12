package com.ketangpai.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ketangpai.activity.ChatActivity;
import com.ketangpai.adapter.MessageAdapter;
import com.ketangpai.base.BasePresenterFragment;
import com.ketangpai.bean.NewestMessage;
import com.ketangpai.bean.User;
import com.ketangpai.listener.OnItemClickListener;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.presenter.MessagePresenter;
import com.ketangpai.viewInterface.MessageViewInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by nan on 2016/4/17.
 */
public class MessageFragment extends BasePresenterFragment<MessageViewInterface, MessagePresenter> implements MessageViewInterface, OnItemClickListener {

    //view
    RecyclerView list_messages;

    //adater
    MessageAdapter mMessageAdapter;

    //变量
    private String account;
    List<NewestMessage> mMessages;

    @Override
    protected void initVarious() {
        super.initVarious();
        account = mContext.getSharedPreferences("user", 0).getString("account", "");
    }


    @Override
    protected MessagePresenter createPresenter() {
        return new MessagePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView() {
        list_messages = (RecyclerView) view.findViewById(R.id.list_messagme);

        list_messages.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mMessages = new ArrayList();
        mMessageAdapter = new MessageAdapter(mContext, mMessages, account);
        list_messages.setAdapter(mMessageAdapter);
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initListener() {
        mMessageAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        mPresenter.getNewestMessageLis(mContext, account);
    }



    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(mContext, ChatActivity.class);
        NewestMessage newestMessage = mMessages.get(position);
        if (newestMessage.getReceive_account().equals(account)) {
            intent.putExtra("send_user", new User(newestMessage.getSend_account(), newestMessage.getSend_name(), newestMessage.getSend_path()));
        } else {
            intent.putExtra("send_user", new User(newestMessage.getReceive_account(), newestMessage.getReceive_name(), newestMessage.getReceive_path()));
        }
        startActivity(intent);

    }



    @Override
    public void getNewestMessageListOnComplete(List<NewestMessage> newestMessages) {
        mMessages.addAll(newestMessages);
        Collections.reverse(mMessages);
        mMessageAdapter.notifyDataSetChanged();
    }
}
