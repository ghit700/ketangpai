package com.ketangpai.activity;

import android.support.v4.app.Fragment;
import com.ketangpai.nan.ketangpai.R;

import com.ketangpai.base.BaseToolbarActivity;
import com.ketangpai.fragment.AddExamTitleFragment;

/**
 * Created by nan on 2016/5/4.
 */
public class AddExamTitleActivity extends BaseToolbarActivity {
    @Override
    protected Fragment getLayoutFragment() {
        return new AddExamTitleFragment();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_base_course_add_tab;
    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        getSupportActionBar().setTitle("新测试");
    }
}
