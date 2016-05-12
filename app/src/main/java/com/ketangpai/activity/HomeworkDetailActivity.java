package com.ketangpai.activity;

import android.support.v4.app.Fragment;
import com.ketangpai.nan.ketangpai.R;

import com.ketangpai.base.BaseToolbarActivity;
import com.ketangpai.bean.Student_Homework;
import com.ketangpai.fragment.HomeworkDetailFragment;

/**
 * Created by nan on 2016/5/1.
 */
public class HomeworkDetailActivity extends BaseToolbarActivity {


    private Student_Homework homework;

    @Override
    protected void initVariables() {
        super.initVariables();
        homework = (Student_Homework) getIntent().getSerializableExtra("homework");
    }

    @Override
    protected Fragment getLayoutFragment() {
        return new HomeworkDetailFragment();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_base_course_add_tab;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        getSupportActionBar().setTitle(homework.getStudent_name());
    }
}
