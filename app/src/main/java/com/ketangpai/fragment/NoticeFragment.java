package com.ketangpai.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ketangpai.activity.DataActivity;
import com.ketangpai.adapter.DataAdapter;
import com.ketangpai.base.BaseFragment;
import com.ketangpai.bean.Data;
import com.ketangpai.bean.Notice;
import com.ketangpai.listener.OnItemClickListener;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.utils.TimeUtils;
import com.ketangpai.view.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administra
 * tor on 2016/4/15.
 */
public class NoticeFragment extends BaseFragment implements OnItemClickListener {

    //view
    private TextView tv_notice_time;
    private TextView tv_notice_content;
    private RecyclerView list_notice_data;

    //adapter
    private DataAdapter mDataAdapter;

    //变量
    private List<Data> mDataList;
    private Notice mNotice;

    @Override
    protected void initVarious() {
        super.initVarious();
        if (null != getActivity().getIntent().getSerializableExtra("notice")) {
            mNotice = (Notice) getActivity().getIntent().getSerializableExtra("notice");
            mDataList = mNotice.getFiles();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notice;
    }

    @Override
    protected void initView() {
        tv_notice_time = (TextView) view.findViewById(R.id.tv_notice_publishTime);
        tv_notice_content = (TextView) view.findViewById(R.id.tv_notice_content);
        initNoticeDataList();

        tv_notice_time.setText(TimeUtils.getNoticeTime(mNotice.getTime()));
        tv_notice_content.setText(mNotice.getContent());

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mDataAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {

    }


    private void initNoticeDataList() {
        list_notice_data = (RecyclerView) view.findViewById(R.id.list_notice_data);
        list_notice_data.setLayoutManager(new FullyLinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        if (null == mDataList) {
            mDataList = new ArrayList<Data>();
        }

        mDataAdapter = new DataAdapter(mContext, mDataList);
        list_notice_data.setAdapter(mDataAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Data bmobFile = mDataList.get(position);
        Intent intent = new Intent(mContext, DataActivity.class);
        intent.putExtra("name", bmobFile.getName());
        intent.putExtra("url", bmobFile.getUrl());
        startActivity(intent);
    }
}
