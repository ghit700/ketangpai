package com.ketangpai.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.ketangpai.base.BaseAdapter;
import com.ketangpai.bean.Data;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.utils.ImageLoaderUtils;

import java.util.List;

/**
 * Created by nan on 2016/3/17.
 */
public class CourseDataAdapter extends BaseAdapter<Data> {

    public CourseDataAdapter(Context mContext, List<Data> mDataList) {
        super(mContext, mDataList);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_course_data;
    }

    @Override
    protected void bindData(ViewHolder holder, int position, Data item) {
        TextView mFileNameText = (TextView) holder.getViewById(R.id.tv_share_fileName);
        ImageView mFilePicImg = (ImageView) holder.getViewById(R.id.img_share_picImg);
        TextView mFileSize = (TextView) holder.getViewById(R.id.tv_share_fileSize);

        mFileNameText.setText(item.getName());
        mFileSize.setText(item.getSize() + "MB");

//        ImageLoaderUtils.display(mContext, mFilePicImg, item.getUrl());
        ImageLoaderUtils.displayByFileName(mContext, mFilePicImg, item.getUrl(), item.getName());

    }


}
