package com.ketangpai.activity;

import android.support.v4.app.Fragment;

import com.ketangpai.base.BaseToolbarActivity;
import com.ketangpai.fragment.DataFragment;
import com.ketangpai.nan.ketangpai.R;

/**
 * Created by Administrator on 2016/4/15.
 */
public class DataActivity extends BaseToolbarActivity {

    private String mDataName;

    @Override
    protected void initVariables() {
        super.initVariables();
        if (null != getIntent().getStringExtra("name")) {

            mDataName = getIntent().getStringExtra("name");
        }
    }

    @Override
    protected Fragment getLayoutFragment() {
        return new DataFragment();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_base_course_add_tab;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    protected void loadData() {
        super.loadData();
    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        getSupportActionBar().setTitle(mDataName);
    }
}
