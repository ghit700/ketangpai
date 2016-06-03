package com.ketangpai.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ketangpai.base.BaseAdapter;
import com.ketangpai.bean.Student;
import com.ketangpai.bean.User;
import com.ketangpai.nan.ketangpai.R;

import java.util.List;

/**
 * Created by nan on 2016/5/29.
 */
public class StudentAdapter extends BaseAdapter<Student> {
    public StudentAdapter(Context mContext, List<Student> mDataList) {
        super(mContext, mDataList);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_list_student;
    }

    @Override
    protected void bindData(ViewHolder holder, int position, final Student item) {
        super.bindData(holder, position, item);

        CheckBox cbItemListStudent = (CheckBox) holder.getViewById(R.id.cbItemListStudent);
        TextView tvItemListStudentName = (TextView) holder.getViewById(R.id.tvItemListStudentName);

        cbItemListStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setCheck(isChecked);
            }
        });

        cbItemListStudent.setChecked(item.isCheck());
        tvItemListStudentName.setText(item.getName());

    }
}
