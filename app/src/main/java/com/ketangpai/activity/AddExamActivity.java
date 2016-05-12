package com.ketangpai.activity;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.ketangpai.nan.ketangpai.R;

import com.ketangpai.base.BaseToolbarActivity;
import com.ketangpai.fragment.AddExamFragment;

/**
 * Created by nan on 2016/5/3.
 */
public class AddExamActivity extends BaseToolbarActivity {

    private AddExamFragment mFragment;

    @Override
    protected Fragment getLayoutFragment() {
        mFragment = new AddExamFragment();
        return mFragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_base_course_add_tab;
    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_exam_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.send) {
            mFragment.publishExam();
        }
        return super.onOptionsItemSelected(item);
    }
}
