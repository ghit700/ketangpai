package com.ketangpai.activity;

import android.support.v4.app.Fragment;

import com.ketangpai.base.BaseToolbarActivity;
import com.ketangpai.bean.Notice;
import com.ketangpai.fragment.NoticeFragment;
import com.ketangpai.nan.ketangpai.R;

/**
 * Created by Administrator on 2016/4/15.
 */
public class NoticeActivity extends BaseToolbarActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_base_course_add_tab;
    }

    @Override
    protected Fragment getLayoutFragment() {
        return new NoticeFragment();
    }

    @Override
    protected void initVariables() {
        super.initVariables();

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
        if (null != getIntent().getSerializableExtra("notice")) {
            getSupportActionBar().setTitle(((Notice) getIntent().getSerializableExtra("notice")).getTitle());
        }
    }
}
