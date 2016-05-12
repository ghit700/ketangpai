package com.ketangpai.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ketangpai.activity.ExamDetailActivity;
import com.ketangpai.adapter.ExamkStudentListAdapter;
import com.ketangpai.base.BasePresenterFragment;
import com.ketangpai.bean.Student_Reply;
import com.ketangpai.bean.Test;
import com.ketangpai.listener.OnItemClickListener;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.presenter.ExamPresenter;
import com.ketangpai.viewInterface.ExamViewInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by nan on 2016/5/8.
 */
public class ExamFragment extends BasePresenterFragment<ExamViewInterface, ExamPresenter> implements ExamViewInterface, SwipeRefreshLayout.OnRefreshListener, OnItemClickListener {

    @InjectView(R.id.refesh_exam)
    SwipeRefreshLayout refeshExam;
    @InjectView(R.id.list_t_exam)
    RecyclerView listTExam;

    //adapter
    ExamkStudentListAdapter mExamkStudentListAdapter;

    //view
    List<Student_Reply> mStudent_replies;
    private Test test;


    @Override
    protected int getLayoutId() {

        return R.layout.fragment_exam;
    }

    @Override
    protected void initVarious() {
        super.initVarious();
        test = (Test) getActivity().getIntent().getSerializableExtra("test");
    }

    @Override
    protected void initView() {
        listTExam.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mStudent_replies = new ArrayList<>();
        mExamkStudentListAdapter = new ExamkStudentListAdapter(mContext, mStudent_replies);
        listTExam.setAdapter(mExamkStudentListAdapter);
    }

    @Override
    protected void initData() {
        refeshExam.setColorSchemeResources(R.color.colorPrimary);
    }

    @Override
    protected void initListener() {
        refeshExam.setOnRefreshListener(this);
        mExamkStudentListAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        refeshExam.post(new Runnable() {
            @Override
            public void run() {
                refeshExam.setRefreshing(true);
            }
        });
        onRefresh();
    }

    @Override
    protected ExamPresenter createPresenter() {
        return new ExamPresenter();
    }


    @Override
    public void onRefresh() {
        mPresenter.getStudentReplykList(mContext, test.getT_id());
    }

    @Override
    public void onItemClick(View view, int position) {
        if (mStudent_replies.get(position).getS_state().equals("按时交") || mStudent_replies.get(position).getS_state().equals("逾时未交")) {
            Intent intent = new Intent(mContext, ExamDetailActivity.class);
            intent.putExtra("student_reply", mStudent_replies.get(position));
            startActivity(intent);
        }
    }

    @Override
    public void getStudentReplyListOnComplete(List<Student_Reply> student_replies) {
        refeshExam.setRefreshing(false);
        mStudent_replies.clear();
        mStudent_replies.addAll(student_replies);
        Collections.reverse(mStudent_replies);
        mExamkStudentListAdapter.notifyDataSetChanged();
    }
}
