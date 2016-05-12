package com.ketangpai.activity;

import android.support.v4.app.Fragment;

import com.ketangpai.base.BaseToolbarActivity;
import com.ketangpai.bean.Course;
import com.ketangpai.fragment.CourseFragment;
import com.ketangpai.nan.ketangpai.R;

/**
 * Created by nan on 2016/3/16.
 */
public class CourseActivity extends BaseToolbarActivity {


    //view


    //变量
    private Course mCourse;
    private CourseFragment mFragment;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_base_course_add_tab;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        if (getIntent().getSerializableExtra("course") != null) {
            mCourse = (Course) getIntent().getSerializableExtra("course");

        }

    }

    @Override
    protected Fragment getLayoutFragment() {
        mFragment = new CourseFragment();
        return mFragment;
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
        getSupportActionBar().setTitle(mCourse.getName());

    }

    public Course getCourse() {
        return mCourse;
    }
}
