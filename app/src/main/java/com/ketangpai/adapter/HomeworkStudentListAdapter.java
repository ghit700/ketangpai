package com.ketangpai.adapter;

import android.content.Context;
import android.widget.TextView;

import com.ketangpai.base.BaseAdapter;
import com.ketangpai.bean.Student_Homework;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.utils.TimeUtils;

import java.util.List;

/**
 * Created by nan on 2016/5/1.
 */
public class HomeworkStudentListAdapter extends BaseAdapter<Student_Homework> {


    public HomeworkStudentListAdapter(Context mContext, List<Student_Homework> mDataList) {
        super(mContext, mDataList);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_homework;
    }

    @Override
    protected void bindData(ViewHolder holder, int position, Student_Homework item) {
        super.bindData(holder, position, item);

        TextView tv_t_homework_student_number = (TextView) holder.getViewById(R.id.tv_t_homework_student_number);
        TextView tv_t_homework_student_name = (TextView) holder.getViewById(R.id.tv_t_homework_student_name);
        TextView tv_t_homework_student_grade = (TextView) holder.getViewById(R.id.tv_t_homework_student_grade);
        TextView tv_t_homework_commit_time = (TextView) holder.getViewById(R.id.tv_t_homework_commit_time);
        TextView tv_t_homework_s_state = (TextView) holder.getViewById(R.id.tv_t_homework_s_state);


        tv_t_homework_student_name.setText(item.getStudent_name());
        tv_t_homework_student_number.setText(String.valueOf(item.getStudent_number()));
        if (item.getT_state().equals("未批改")) {
            tv_t_homework_student_grade.setText("未批改");
        } else if (null == item.getGrade()) {
            tv_t_homework_student_grade.setText("");
        } else {
            tv_t_homework_student_grade.setText(String.valueOf(item.getGrade()));
        }
        if (item.getS_state().equals("未交")) {
            tv_t_homework_commit_time.setText("未交");
        } else {
            tv_t_homework_s_state.setText(item.getS_state());
            tv_t_homework_commit_time.setText("提交时间:" + TimeUtils.getNoticeTime(item.getCommit_time()));
        }
    }
}
