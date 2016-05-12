package com.ketangpai.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ketangpai.activity.HomeworkDetailActivity;
import com.ketangpai.adapter.HomeworkStudentListAdapter;
import com.ketangpai.base.BasePresenterFragment;
import com.ketangpai.bean.Student_Homework;
import com.ketangpai.bean.Teacher_Homework;
import com.ketangpai.listener.OnItemClickListener;
import com.ketangpai.presenter.HomeworkPresenter;
import com.ketangpai.viewInterface.HomeworkViewInterface;
import com.ketangpai.nan.ketangpai.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by nan on 2016/5/1.
 */
public class HomeworkFragment extends BasePresenterFragment<HomeworkViewInterface, HomeworkPresenter> implements HomeworkViewInterface, OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {


    public static final String TAG = "===HomeworkFragment";

    @InjectView(R.id.list_t_homework)
    RecyclerView listTHomework;
    @InjectView(R.id.refesh_homework)
    SwipeRefreshLayout refeshHomework;

    //adapter
    private HomeworkStudentListAdapter mHomeworkStudentListAdapter;

    //various
    private Teacher_Homework homework;
    private List<Student_Homework> mHomeworks;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homework;
    }

    @Override
    protected void initVarious() {
        super.initVarious();
        homework = (Teacher_Homework) getActivity().getIntent().getSerializableExtra("homework");
    }

    @Override
    protected void initView() {
        super.initView();
        listTHomework.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mHomeworks = new ArrayList<>();
        mHomeworkStudentListAdapter = new HomeworkStudentListAdapter(mContext, mHomeworks);
        listTHomework.setAdapter(mHomeworkStudentListAdapter);
    }

    @Override
    protected void initData() {
        super.initData();

        refeshHomework.setColorSchemeResources(R.color.colorPrimary);

    }

    @Override
    protected void initListener() {
        super.initListener();
        mHomeworkStudentListAdapter.setOnItemClickListener(this);
        refeshHomework.setOnRefreshListener(this);
    }

    @Override
    protected void loadData() {
        super.loadData();
        refeshHomework.post(new Runnable() {
            @Override
            public void run() {
                refeshHomework.setRefreshing(true);
            }
        });
        onRefresh();
    }

    @Override
    public void onItemClick(View view, int position) {
        if (mHomeworks.get(position).getS_state().equals("按时交") || mHomeworks.get(position).getS_state().equals("逾时未交")) {
            Intent intent = new Intent(mContext, HomeworkDetailActivity.class);
            intent.putExtra("homework", mHomeworks.get(position));
            startActivity(intent);
        }
    }


    @Override
    protected HomeworkPresenter createPresenter() {
        return new HomeworkPresenter();
    }

    @Override
    public void getStudentHomeworkListOnComplete(List<Student_Homework> homeworklist) {
        refeshHomework.setRefreshing(false);
        mHomeworks.addAll(homeworklist);
        Collections.reverse(mHomeworks);
        mHomeworkStudentListAdapter.notifyDataSetChanged();
    }


    @Override
    public void onRefresh() {
        mHomeworks.clear();
        mPresenter.getStudentHomeworkList(mContext, homework.getH_id());
    }
}
