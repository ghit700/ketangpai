package com.ketangpai.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ketangpai.activity.DataActivity;
import com.ketangpai.adapter.DataAdapter;
import com.ketangpai.base.BasePresenterFragment;
import com.ketangpai.bean.Data;
import com.ketangpai.bean.Student_Homework;
import com.ketangpai.listener.OnItemClickListener;
import com.ketangpai.presenter.HomeworkDetailPresenter;
import com.ketangpai.view.FullyLinearLayoutManager;
import com.ketangpai.viewInterface.HomeworkDetailViewInterface;
import com.ketangpai.nan.ketangpai.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by nan on 2016/5/1.
 */
public class HomeworkDetailFragment extends BasePresenterFragment<HomeworkDetailViewInterface, HomeworkDetailPresenter> implements HomeworkDetailViewInterface {
    @InjectView(R.id.tv_t_detail_homework_student_name)
    TextView tvTDetailHomeworkStudentName;
    @InjectView(R.id.tv_t_detail_homework_student_number)
    TextView tvTDetailHomeworkStudentNumber;
    @InjectView(R.id.et_t_detail_homework_grade)
    EditText etTDetailHomeworkGrade;
    @InjectView(R.id.tv_t_detail_homework_student_state)
    TextView tvTDetailHomeworkStudentState;
    @InjectView(R.id.list_homework)
    RecyclerView listHomework;
    @InjectView(R.id.et_s_homework_comment)
    EditText etSHomeworkComment;
    @InjectView(R.id.btn_correct_homework)
    Button btnCorrectHomework;

    //adapter
    DataAdapter mDataAdapter;


    //various
    private Student_Homework homework;
    private List<Data> mStudentHomeworks;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_homework;
    }

    @Override
    protected void initVarious() {
        super.initVarious();
        homework = (Student_Homework) getActivity().getIntent().getSerializableExtra("homework");

    }

    @Override
    protected void initView() {
        super.initView();

        listHomework.setLayoutManager(new FullyLinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        if (null != homework.getHomeworks()) {
            mStudentHomeworks = homework.getHomeworks();
        } else {
            mStudentHomeworks = new ArrayList<>();
        }
        mDataAdapter = new DataAdapter(mContext, mStudentHomeworks);
        listHomework.setAdapter(mDataAdapter);

        tvTDetailHomeworkStudentName.setText(homework.getStudent_name());
        tvTDetailHomeworkStudentNumber.setText(String.valueOf(homework.getStudent_number()));

        tvTDetailHomeworkStudentState.setText(homework.getS_state());


        if (null != homework.getGrade()) {
            etSHomeworkComment.setText(homework.getComment());
            etTDetailHomeworkGrade.setText(String.valueOf(homework.getGrade()));
            //不能修改et
            etTDetailHomeworkGrade.setFocusable(false);
            etTDetailHomeworkGrade.setFocusableInTouchMode(false);

            etSHomeworkComment.setFocusable(false);
            etSHomeworkComment.setFocusableInTouchMode(false);

            btnCorrectHomework.setVisibility(View.GONE);
        }


    }


    @Override
    protected void initListener() {
        super.initListener();
        mDataAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, DataActivity.class);
                intent.putExtra("name", mStudentHomeworks.get(position).getName());
                intent.putExtra("url", mStudentHomeworks.get(position).getUrl());
                startActivity(intent);
            }
        });

    }

    @Override
    protected HomeworkDetailPresenter createPresenter() {
        return new HomeworkDetailPresenter();
    }


    @OnClick(R.id.btn_correct_homework)
    public void onBtn_correct_homeworkClick() {
        int grade = Integer.parseInt(etTDetailHomeworkGrade.getText().toString());
        if (homework.getS_state().equals("未交")) {
            sendToast("学生还未提交作业");
        } else if (etTDetailHomeworkGrade.getText().toString().equals("")) {
            sendToast("请输入作业分数");
        } else if (grade > 100 || grade <= 0) {
            sendToast("请输入正确的分数(1-100)");
        } else {
            homework.setGrade(grade);
            homework.setComment(etSHomeworkComment.getText().toString());
            homework.setT_state("已批改");
            mPresenter.correctHomework(mContext, homework);
            getActivity().finish();
        }
    }
}
