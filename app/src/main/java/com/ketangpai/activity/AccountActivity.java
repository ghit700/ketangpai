package com.ketangpai.activity;

import android.support.v4.app.Fragment;

import com.ketangpai.base.BaseToolbarActivity;
import com.ketangpai.fragment.AccountFragment;
import com.ketangpai.nan.ketangpai.R;

/**
 * Created by nan on 2016/3/21.
 */
public class AccountActivity extends BaseToolbarActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_base_course_add_tab;
    }

    @Override
    protected Fragment getLayoutFragment() {
        return new AccountFragment();
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

    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        getSupportActionBar().setTitle("个人信息");

    }
}
