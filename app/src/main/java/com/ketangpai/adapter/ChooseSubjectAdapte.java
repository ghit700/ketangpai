package com.ketangpai.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ketangpai.base.BaseAdapter;
import com.ketangpai.bean.Subject;
import com.ketangpai.nan.ketangpai.R;

import java.util.List;

/**
 * Created by nan on 2016/5/12.
 */
public class ChooseSubjectAdapte extends BaseAdapter<Subject> {
    public ChooseSubjectAdapte(Context mContext, List<Subject> mDataList) {
        super(mContext, mDataList);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_choose_exam_subject;
    }

    @Override
    protected void bindData(ViewHolder holder, int position, final Subject item) {
        CheckBox cbChoose = (CheckBox) holder.getViewById(R.id.cb_choose);
        TextView tvExamGrade = (TextView) holder.getViewById(R.id.tv_exam_grade);
        TextView tvExamTitle = (TextView) holder.getViewById(R.id.tv_exam_title);

        cbChoose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setCheck(isChecked);
            }
        });


        cbChoose.setChecked(item.isCheck());
        tvExamGrade.setText(String.valueOf(item.getScore()));
        tvExamTitle.setText(item.getTitle());


    }
}
