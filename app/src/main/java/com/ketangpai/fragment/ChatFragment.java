package com.ketangpai.fragment;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ketangpai.adapter.ChatAdapter;
import com.ketangpai.base.BasePresenterFragment;
import com.ketangpai.bean.MessageInfo;
import com.ketangpai.bean.User;
import com.ketangpai.event.ReceiveMessageEvent;
import com.ketangpai.listener.OnItemClickListener;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.presenter.ChatPresenter;
import com.ketangpai.viewInterface.ChatViewInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nan on 2016/3/19.
 */
public class ChatFragment extends BasePresenterFragment<ChatViewInterface, ChatPresenter> implements ChatViewInterface, View.OnClickListener, TextWatcher, OnItemClickListener {

    public final static String TAG = "====ChatFragment";
    //view
    EditText mSendTextEt;
    ImageView mSendtBtn;
    RecyclerView mChatList;
    RelativeLayout mRl;

    //adapter
    ChatAdapter mChatAdapter;

    //变量
    List<MessageInfo> mChatRecondList;
    private String name;
    private String path;
    private String account;
    private User mSend_User;
    private InputMethodManager mImm;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void initVarious() {
        super.initVarious();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        EventBus.getDefault().register(this);
        mImm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        account = mContext.getSharedPreferences("user", 0).getString("account", "");
        name = mContext.getSharedPreferences("user", 0).getString("name", "");
        path = mContext.getSharedPreferences("user", 0).getString("path", "");
        if (null != getActivity().getIntent().getSerializableExtra("send_user")) {
            mSend_User = (User) getActivity().getIntent().getSerializableExtra("send_user");
        }


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initView() {
        mSendtBtn = (ImageView) view.findViewById(R.id.img_chat_send);
        mSendTextEt = (EditText) view.findViewById(R.id.et_chat_sendText);
        mRl = (RelativeLayout) view.findViewById(R.id.rl_chat);
        initChatList();


//        mSwipeRefreshLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                mSwipeRefreshLayout.setRefreshing(true);
//            }
//        });
    }

    @Override
    protected void initData() {

        mChatList.scrollToPosition(9);
    }

    @Override
    protected void initListener() {
        mSendTextEt.setOnClickListener(this);
        mSendTextEt.addTextChangedListener(this);
        mSendtBtn.setOnClickListener(this);
        mRl.setOnClickListener(this);
        mChatAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        mPresenter.getChatRecondList(mContext, account, mSend_User.getAccount());
    }

    private void initChatList() {
        mChatList = (RecyclerView) view.findViewById(R.id.list_chat);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mChatList.setLayoutManager(linearLayoutManager);
        mChatRecondList = new ArrayList();
        mChatAdapter = new ChatAdapter(mContext, mChatRecondList, account, path);
        mChatList.setAdapter(mChatAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_chat_sendText:
                break;
            case R.id.img_chat_send:
                sendMessage();
                break;
            case R.id.rl_chat:
                mImm.hideSoftInputFromInputMethod(v.getWindowToken(), 0);
                break;
            default:
                break;
        }
    }

    private void sendMessage() {
        String content = mSendTextEt.getText().toString();
        mSendTextEt.setText("");
        if (!content.equals("")) {
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setContent(content);
            messageInfo.setTime(System.currentTimeMillis());
            messageInfo.setReceive_account(mSend_User.getAccount());
            messageInfo.setReceive_name(mSend_User.getName());
            messageInfo.setSend_account(account);
            messageInfo.setSend_name(name);
            messageInfo.setSend_path(path);
            mChatAdapter.addItem(mChatRecondList.size(), messageInfo);
            mPresenter.sendMessage(mContext, messageInfo, mSend_User.getPath());
            mChatList.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mChatList.smoothScrollToPosition(mChatRecondList.size());
                }
            }, 200);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (mSendTextEt.length() == 0) {
            mSendtBtn.setImageResource(R.drawable.ic_send_default);
        } else {
            mSendtBtn.setImageResource(R.drawable.ic_send_light);
        }
    }

    @Override
    protected ChatPresenter createPresenter() {
        return new ChatPresenter();
    }

    @Override
    public void getChatRecondListOnComplete(List<MessageInfo> messageInfos) {
        mChatRecondList.addAll(messageInfos);
        mChatAdapter.notifyDataSetChanged();
        mChatList.postDelayed(new Runnable() {
            @Override
            public void run() {
                mChatList.smoothScrollToPosition(mChatRecondList.size());
            }
        }, 200);
    }

    @Subscribe
    public void onReceiveMessageEvent(ReceiveMessageEvent event) {
        if (event.getMessageInfo().getSend_account().equals(mSend_User.getAccount())) {
            NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(0);
            mChatAdapter.addItem(mChatRecondList.size(), event.getMessageInfo());
            mChatList.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mChatList.smoothScrollToPosition(mChatRecondList.size());
                }
            }, 200);

        }


    }

    @Override
    public void onItemClick(View view, int position) {
        mImm.hideSoftInputFromInputMethod(view.getWindowToken(), 0);

    }
}
