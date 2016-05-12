package com.ketangpai.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ketangpai.base.BaseActivity;
import com.ketangpai.base.BaseAdapter;
import com.ketangpai.bean.Subject;
import com.ketangpai.nan.ketangpai.R;

import java.util.List;

/**
 * Created by nan on 2016/5/8.
 */
public class ExamAdapter extends BaseAdapter<Subject> {

    public ExamAdapter(Context mContext, List<Subject> mDataList) {
        super(mContext, mDataList);
    }

    @Override
    public int getItemViewType(int position) {
        int i = 0;
        switch (mDataList.get(position).getType()) {
            case 1:
                i = 1;
                break;
            case 2:
                i = 2;
                break;
            case 3:
                i = 3;
                break;
            case 4:
                i = 4;
                break;
        }
        return i;
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        switch (viewType) {
            case 1:
                return R.layout.item_exam_judge;
            case 2:
                return R.layout.item_exam_single;
            case 3:
                return R.layout.item_exam_mutiple;
            case 4:
                return R.layout.item_exam_short;
        }
        return 1;
    }

    @Override
    protected void bindData(ViewHolder holder, int position, Subject item) {
        super.bindData(holder, position, item);
        switch (item.getType()) {
            case 1:
                bindJudgeData(holder, position, item);
                break;
            case 2:
                bindSingleData(holder, position, item);
                break;
            case 3:
                bindMultipleData(holder, position, item);
                break;
            case 4:
                bindShortData(holder, position, item);
                break;

            default:
                break;
        }
    }

    private void bindShortData(ViewHolder holder, int position, final Subject item) {
        TextView tvExamShort = (TextView) holder.getViewById(R.id.tv_exam_short);
        TextView tvExamGrade = (TextView) holder.getViewById(R.id.tv_exam_grade);
        TextView tvExamValue = (TextView) holder.getViewById(R.id.tv_exam_value);
        final TextView etExamValue = (TextView) holder.getViewById(R.id.et_exam_value);
        TextView tvExamTitle = (TextView) holder.getViewById(R.id.tv_exam_title);
        TextView tvExamContent = (TextView) holder.getViewById(R.id.tv_exam_content);


        int i = position;
        i++;
        tvExamShort.setText(i + ": " + "简答题");

        tvExamGrade.setText(String.valueOf(item.getScore()));


        if (item.getS_value() == null) {
            tvExamValue.setVisibility(View.GONE);
            etExamValue.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!etExamValue.getText().toString().equals("")) {
                        int value = Integer.parseInt(etExamValue.getText().toString());
                        if (value > item.getScore()) {
                            ((BaseActivity) mContext).sendToast("不能输入比分值大的数字");
                        } else {
                            item.setS_value(value);
                        }

                    }


                }
            });
        } else {
            etExamValue.setVisibility(View.GONE);
            if (null != item.getS_value()) {
                tvExamValue.setText(String.valueOf(item.getS_value()));
            } else {
                tvExamValue.setText("");
            }
        }


        tvExamContent.setText(item.getAnswer());


        tvExamTitle.setText(item.getTitle());


    }

    private void bindMultipleData(ViewHolder holder, int position, final Subject item) {
        TextView tvExamMutiple = (TextView) holder.getViewById(R.id.tv_exam_mutiple);
        TextView tvExamGrade = (TextView) holder.getViewById(R.id.tv_exam_grade);
        TextView tvExamValue = (TextView) holder.getViewById(R.id.tv_exam_value);
        TextView tvExamTitle = (TextView) holder.getViewById(R.id.tv_exam_title);
        final CheckBox cbExamA = (CheckBox) holder.getViewById(R.id.cb_exam_a);
        final CheckBox cbExamB = (CheckBox) holder.getViewById(R.id.cb_exam_b);
        final CheckBox cbExamC = (CheckBox) holder.getViewById(R.id.cb_exam_c);
        final CheckBox cbExamD = (CheckBox) holder.getViewById(R.id.cb_exam_d);


        int i = position;

        i++;
        tvExamMutiple.setText(i + ": " + "多选题");

        tvExamGrade.setText(String.valueOf(item.getScore()));


        tvExamValue.setText(String.valueOf(item.getS_value()));
        cbExamA.setEnabled(false);
        cbExamB.setEnabled(false);
        cbExamC.setEnabled(false);
        cbExamD.setEnabled(false);

        String[] answer = item.getAnswer().split(",");
        if (answer[0].equals("true")) {
            cbExamA.setChecked(true);
        } else {
            cbExamA.setChecked(false);
        }
        if (answer[1].equals("true")) {
            cbExamB.setChecked(true);
        } else {
            cbExamB.setChecked(false);
        }
        if (answer[2].equals("true")) {
            cbExamC.setChecked(true);
        } else {
            cbExamC.setChecked(false);
        }
        if (answer[3].equals("true")) {
            cbExamD.setChecked(true);
        } else {
            cbExamD.setChecked(false);
        }


        tvExamTitle.setText(item.getTitle());


        cbExamA.setText(item.getContent().get(0));
        cbExamB.setText(item.getContent().get(1));
        cbExamC.setText(item.getContent().get(2));
        cbExamD.setText(item.getContent().get(3));


    }

    private void bindSingleData(ViewHolder holder, int position, final Subject item) {
        TextView tvExamSingle = (TextView) holder.getViewById(R.id.tv_exam_single);
        TextView tvExamGrade = (TextView) holder.getViewById(R.id.tv_exam_grade);
        TextView tvExamValue = (TextView) holder.getViewById(R.id.tv_exam_value);
        TextView tvExamTitle = (TextView) holder.getViewById(R.id.tv_exam_title);
        RadioGroup rgExam = (RadioGroup) holder.getViewById(R.id.rg_exam);
        final RadioButton rbExamA = (RadioButton) holder.getViewById(R.id.rb_exam_a);
        final RadioButton rbExamB = (RadioButton) holder.getViewById(R.id.rb_exam_b);
        final RadioButton rbExamC = (RadioButton) holder.getViewById(R.id.rb_exam_c);
        final RadioButton rbExamD = (RadioButton) holder.getViewById(R.id.rb_exam_d);

        int i = position;
        i++;
        tvExamSingle.setText(i + ": " + "单选题");

        tvExamGrade.setText(String.valueOf(item.getScore()));
        tvExamValue.setText(String.valueOf(item.getS_value()));
        rbExamA.setEnabled(false);
        rbExamB.setEnabled(false);
        rbExamC.setEnabled(false);
        rbExamD.setEnabled(false);
        if (item.getAnswer().equals("a")) {
            rbExamA.setChecked(true);
        }
        if (item.getAnswer().equals("b")) {
            rbExamB.setChecked(true);
        }
        if (item.getAnswer().equals("c")) {
            rbExamC.setChecked(true);
        }
        if (item.getAnswer().equals("d")) {
            rbExamD.setChecked(true);
        }


        tvExamTitle.setText(item.getTitle());

        rbExamA.setText(item.getContent().get(0));
        rbExamB.setText(item.getContent().get(1));
        rbExamC.setText(item.getContent().get(2));
        rbExamD.setText(item.getContent().get(3));


    }

    private void bindJudgeData(ViewHolder holder, int position, final Subject item) {
        TextView tvExamJudge = (TextView) holder.getViewById(R.id.tv_exam_judge);
        TextView tvExamGrade = (TextView) holder.getViewById(R.id.tv_exam_grade);
        TextView tvExamValue = (TextView) holder.getViewById(R.id.tv_exam_value);
        TextView tvExamTitle = (TextView) holder.getViewById(R.id.tv_exam_title);
        RadioGroup rgExam = (RadioGroup) holder.getViewById(R.id.rg_exam);
        RadioButton rbExamTrue = (RadioButton) holder.getViewById(R.id.rb_exam_true);
        RadioButton rbExamFalse = (RadioButton) holder.getViewById(R.id.rb_exam_false);


        int i = position;
        i++;
        tvExamJudge.setText(i + ": " + "判断题");

        tvExamGrade.setText(String.valueOf(item.getScore()));
        tvExamValue.setText(String.valueOf(item.getS_value()));
        rgExam.setEnabled(false);
        rbExamTrue.setEnabled(false);
        rbExamFalse.setEnabled(false);
        if (item.getAnswer().equals("true")) {
            rbExamTrue.setChecked(true);
        } else {
            rbExamFalse.setChecked(true);
        }


        tvExamTitle.setText(item.getTitle());


    }
}
