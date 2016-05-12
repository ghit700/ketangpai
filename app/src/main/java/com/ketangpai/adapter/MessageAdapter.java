package com.ketangpai.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.ketangpai.base.BaseAdapter;
import com.ketangpai.bean.NewestMessage;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.utils.ImageLoaderUtils;
import com.ketangpai.utils.TimeUtils;

import java.util.List;


/**
 * Created by nan on 2016/4/17.
 */
public class MessageAdapter extends BaseAdapter<NewestMessage> {
    private String mAccount;

    public MessageAdapter(Context mContext, List mDataList, String account) {
        super(mContext, mDataList);
        mAccount = account;
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_message;
    }

    @Override
    protected void bindData(ViewHolder holder, int position, NewestMessage item) {
        ImageView img_message = (ImageView) holder.getViewById(R.id.img_message);
        TextView tv_message_name = (TextView) holder.getViewById(R.id.tv_message_name);
        TextView tv_message_time = (TextView) holder.getViewById(R.id.tv_message_time);
        TextView tv_message_content = (TextView) holder.getViewById(R.id.tv_message_content);

        holder.itemView.setBackgroundResource(typedValue.resourceId);

        if (item.getReceive_account().equals(mAccount)) {
            ImageLoaderUtils.display(mContext, img_message, item.getSend_path());
            tv_message_name.setText(item.getSend_name());
            tv_message_time.setText(TimeUtils.getNewestMessageTime(item.getTime()));
            tv_message_content.setText(item.getContent());

        } else {
            ImageLoaderUtils.display(mContext, img_message, item.getReceive_path());
            tv_message_name.setText(item.getReceive_name());
            tv_message_time.setText(TimeUtils.getNewestMessageTime(item.getTime()));
            tv_message_content.setText(item.getContent());
        }


    }


}
