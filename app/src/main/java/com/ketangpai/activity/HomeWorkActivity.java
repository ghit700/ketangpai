package com.ketangpai.activity;

import android.support.v4.app.Fragment;
import com.ketangpai.nan.ketangpai.R;

import com.ketangpai.base.BaseToolbarActivity;
import com.ketangpai.bean.Teacher_Homework;
import com.ketangpai.fragment.HomeworkFragment;

/**
 * Created by nan on 2016/4/10.
 */
public class HomeWorkActivity extends BaseToolbarActivity {

    //variables
    private Teacher_Homework homework;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_base_course_add_tab;
    }

    @Override
    protected Fragment getLayoutFragment() {

        return new HomeworkFragment();

    }

    @Override
    protected void initVariables() {
        super.initVariables();
        if (null != getIntent().getSerializableExtra("homework")) {
            homework = (Teacher_Homework) getIntent().getSerializableExtra("homework");
        }
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
        if (null != homework) {
            getSupportActionBar().setTitle(homework.getTitle());
        }
    }
}
