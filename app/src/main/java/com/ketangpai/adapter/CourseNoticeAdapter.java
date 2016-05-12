package com.ketangpai.adapter;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import com.ketangpai.nan.ketangpai.R;

import com.ketangpai.base.BaseAdapter;
import com.ketangpai.bean.Notice;
import com.ketangpai.utils.TimeUtils;
import com.ketangpai.view.MyPopupMenu;

import java.util.List;

/**
 * Created by nan on 2016/3/17.
 */
public class CourseNoticeAdapter extends BaseAdapter<Notice> {


    public CourseNoticeAdapter(Context mContext, List<Notice> mDataList) {
        super(mContext, mDataList);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_course_notice;
    }


    @Override
    protected void bindData(ViewHolder holder, final int position, final Notice s) {
        TextView mNoticeTitleText = (TextView) holder.getViewById(R.id.tv_notice_title);
        TextView mNoticeTimeText = (TextView) holder.getViewById(R.id.tv_notice_publishTime);
        TextView mNoticeContentText = (TextView) holder.getViewById(R.id.tv_notice_content);
        ImageView mNoticeEdit = (ImageView) holder.getViewById(R.id.img_notice_edit);


        //设置点击事件
        final MyPopupMenu mNoticeEditPopupMenu = new MyPopupMenu(mContext, mNoticeEdit, R.menu.notice_edit_menu);
        mNoticeEditPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.notice_delete_menu:
                        s.delete(mContext);
                        deleteItem(position);
                        break;
                    case R.id.notice_edit_menu:
                        break;

                    default:
                        break;
                }
                return false;
            }
        });
        mNoticeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNoticeEditPopupMenu.show();
            }
        });


        //初始化值
        mNoticeTitleText.setText(s.getTitle());
        mNoticeContentText.setText(s.getContent());
        mNoticeTimeText.setText(TimeUtils.getNoticeTime(s.getTime()));
    }


}
