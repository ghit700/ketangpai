package com.ketangpai.activity;

import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import com.ketangpai.nan.ketangpai.R;

import com.ketangpai.base.BaseToolbarActivity;
import com.ketangpai.fragment.AddNoticeFragment;
import com.ketangpai.utils.IntentUtils;

/**
 * Created by nan on 2016/3/27.
 */
public class AddNoticekActivity extends BaseToolbarActivity {

    private AddNoticeFragment mFragment;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_base_course_add_tab;
    }

    @Override
    protected Fragment getLayoutFragment() {
        mFragment = new AddNoticeFragment();
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
        super.loadData();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.send:
                mFragment.publishNotice();
                break;
            case R.id.data:
                IntentUtils.openDocument(mFragment);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_homework_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        getSupportActionBar().setTitle("新公告");
    }
}
