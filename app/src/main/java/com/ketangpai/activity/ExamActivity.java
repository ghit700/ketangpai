package com.ketangpai.activity;

import android.support.v4.app.Fragment;
import com.ketangpai.nan.ketangpai.R;

import com.ketangpai.base.BaseToolbarActivity;
import com.ketangpai.bean.Test;
import com.ketangpai.fragment.ExamFragment;

/**
 * Created by nan on 2016/5/8.
 */
public class ExamActivity extends BaseToolbarActivity {

    private Test test;

    @Override
    protected void initVariables() {
        super.initVariables();
        if (null != getIntent().getSerializableExtra("test")) {
            test = (Test) getIntent().getSerializableExtra("test");
        }
    }

    @Override
    protected Fragment getLayoutFragment() {
        return new ExamFragment();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_base_course_add_tab;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        getSupportActionBar().setTitle(test.getTitle());
    }
}
