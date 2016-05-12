package com.ketangpai.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import com.ketangpai.nan.ketangpai.R;

import com.ketangpai.base.BaseAdapter;
import com.ketangpai.bean.MessageInfo;
import com.ketangpai.utils.ImageLoaderUtils;

import java.util.List;


/**
 * Created by nan on 2016/3/21.
 */
public class ChatAdapter extends BaseAdapter<MessageInfo> {

    private String mAccount;
    private String mPath;

    public ChatAdapter(Context mContext, List<MessageInfo> mDataList, String account, String path) {
        super(mContext, mDataList);
        mAccount = account;
        mPath = path;
    }

    /**
     * 判断是属于发送方还是接收方的视图
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (mDataList.get(position).getReceive_account().equals(mAccount)) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public void addItem(int positon, MessageInfo item) {
        super.addItem(positon, item);
    }

    @Override
    public void deleteItem(int positon) {
        super.deleteItem(positon);
    }

    /**
     * 根据类型 来选择对应的视图
     * @param viewType
     * @return
     */
    @Override
    protected int getItemLayoutId(int viewType) {
        if (viewType == 0) {
            return R.layout.item_chat_send;
        } else {
            return R.layout.item_chat_receiver;
        }

    }

    @Override
    protected void bindData(ViewHolder holder, int position, MessageInfo item) {
        //初始化view
        ImageView UserImg;
        TextView text;

        if (getItemViewType(position) == 0) {
            UserImg = (ImageView) holder.getViewById(R.id.img_chat_acceptor_userIcon);
            text = (TextView) holder.getViewById(R.id.tv_chat_acceptor_text);
            ImageLoaderUtils.display(mContext, UserImg, item.getSend_path());
            text.setText(item.getContent());
        } else {
            UserImg = (ImageView) holder.getViewById(R.id.img_chat_receiver_userIcon);
            text = (TextView) holder.getViewById(R.id.tv_chat_receiver_text);
            ImageLoaderUtils.display(mContext, UserImg, mPath);
            text.setText(item.getContent());
        }

    }


}
