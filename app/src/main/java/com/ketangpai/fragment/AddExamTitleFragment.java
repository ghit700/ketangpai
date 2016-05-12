package com.ketangpai.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import com.ketangpai.nan.ketangpai.R;

import com.ketangpai.activity.AddExamActivity;
import com.ketangpai.base.BaseFragment;
import com.ketangpai.bean.Teacher_Course;
import com.ketangpai.utils.TimeUtils;

import java.util.Calendar;

/**
 * Created by nan on 2016/5/4.
 */
public class AddExamTitleFragment extends BaseFragment implements View.OnClickListener {
    private Button btnExamSumit;
    private EditText etExamTitle;
    private EditText etExamContent;
    private TextView tvExamEndDate;
    private TextView tvExamEndTime;
    private Teacher_Course course;

    //various
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    private Calendar mCalendar;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_addexam_title;
    }


    @Override
    protected void initVarious() {
        super.initVarious();
        course = (Teacher_Course) getActivity().getIntent().getSerializableExtra("course");
    }

    @Override
    protected void initView() {
        btnExamSumit = (Button) view.findViewById(R.id.btn_exam_submit);
        etExamTitle = (EditText) view.findViewById(R.id.et_exam_title);
        etExamContent = (EditText) view.findViewById(R.id.et_exam_content);
        tvExamEndDate = (TextView) view.findViewById(R.id.tv_add_exam_endDate);
        tvExamEndTime = (TextView) view.findViewById(R.id.tv_add_exam_endTime);
    }

    @Override
    protected void initData() {
        mCalendar = Calendar.getInstance();
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH) + 1;
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mCalendar.get(Calendar.MINUTE);
        tvExamEndDate.setText(TimeUtils.getCurrentDateFormat(mCalendar));
        tvExamEndTime.setText(TimeUtils.getCurrentTimeFormat(mCalendar));
    }

    @Override
    protected void initListener() {
        tvExamEndDate.setOnClickListener(this);
        tvExamEndTime.setOnClickListener(this);
        btnExamSumit.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exam_submit:
                if (!etExamTitle.getText().toString().equals("") && !etExamContent.getText().toString().equals("")) {
                    Intent intent = new Intent(mContext, AddExamActivity.class);
                    intent.putExtra("course", course);
                    intent.putExtra("title", etExamTitle.getText().toString());
                    intent.putExtra("content", etExamContent.getText().toString());
                    intent.putExtra("time", mCalendar.getTimeInMillis());
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    sendToast("请输入测试名称和内容");
                }
                break;
            case R.id.tv_add_exam_endDate:
                showDateDialog();
                break;
            case R.id.tv_add_exam_endTime:
                showTimeDialog();
                break;

            default:
                break;
        }
    }

    /**
     * 时间选择器
     */
    private void showTimeDialog() {
        new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay;
                mMinute = minute;
                mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
                mCalendar.set(Calendar.MINUTE, mMinute);
                tvExamEndTime.setText(hourOfDay + ":" + minute);
            }
        }, mHour, mMinute, true).show();
    }

    /**
     * 日期选择器
     */
    private void showDateDialog() {
        new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.i("===wu", year + " " + monthOfYear + " " + dayOfMonth);
                mYear = year;
                mMonth = monthOfYear + 1;
                mDay = dayOfMonth;
                mCalendar.set(mYear, mMonth - 1, mDay);
                tvExamEndDate.setText(mYear + "/" + mMonth + "/" + mDay);
            }
        }, mYear, mMonth - 1, mDay).show();
    }
}
