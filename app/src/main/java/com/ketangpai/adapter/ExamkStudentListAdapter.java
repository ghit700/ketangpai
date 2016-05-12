package com.ketangpai.adapter;

import android.content.Context;
import android.widget.TextView;

import com.ketangpai.base.BaseAdapter;
import com.ketangpai.bean.Student_Reply;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.utils.TimeUtils;

import java.util.List;

/**
 * Created by nan on 2016/5/1.
 */
public class ExamkStudentListAdapter extends BaseAdapter<Student_Reply> {


    public ExamkStudentListAdapter(Context mContext, List<Student_Reply> mDataList) {
        super(mContext, mDataList);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_exam;
    }

    @Override
    protected void bindData(ViewHolder holder, int position, Student_Reply item) {
        super.bindData(holder, position, item);

        TextView tvExamStudentNumber = (TextView) holder.getViewById(R.id.tv_t_exam_student_number);
        TextView tvExamStudentName = (TextView) holder.getViewById(R.id.tv_t_exam_student_name);
        TextView tvExamStudentGrade = (TextView) holder.getViewById(R.id.tv_t_exam_student_grade);
        TextView tvExamCommitTime = (TextView) holder.getViewById(R.id.tv_t_exam_commit_time);
        TextView tvExamSState = (TextView) holder.getViewById(R.id.tv_t_exam_s_state);


        tvExamStudentName.setText(item.getStudent_name());
        tvExamStudentNumber.setText(String.valueOf(item.getStudent_number()));
        if (item.getT_state().equals("未批改")) {
            tvExamStudentGrade.setText("未批改");
        } else if (null == item.getGrade()) {
            tvExamStudentGrade.setText("");
        } else {
            tvExamStudentGrade.setText(String.valueOf(item.getGrade()));
        }
        if (item.getS_state().equals("未交")) {
            tvExamCommitTime.setText("未交");
        } else {
            tvExamSState.setText(item.getS_state());
            tvExamCommitTime.setText("提交时间:" + TimeUtils.getNoticeTime(item.getCommit_time()));
        }
    }
}
