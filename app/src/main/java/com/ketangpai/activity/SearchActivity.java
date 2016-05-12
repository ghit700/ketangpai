package com.ketangpai.activity;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import com.ketangpai.nan.ketangpai.R;

import com.ketangpai.adapter.SearchTabPagerAdapter;
import com.ketangpai.base.BaseActivity;
import com.ketangpai.view.SlidingTabLayout;


/**
 * Created by nan on 2016/3/12.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    //view
    private SlidingTabLayout mTabs;
    private ViewPager mTabViewPager;
    private ImageView mBackBtn;
    private EditText mSearchText;

    //adpter
    private SearchTabPagerAdapter mSearchTabsPagerAdapter;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }

    @Override
    protected void initView() {
        initTabsLayout();
        mBackBtn = (ImageView) findViewById(R.id.img_search_back);
        mSearchText = (EditText) findViewById(R.id.et_search_text);

    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initListener() {
        mBackBtn.setOnClickListener(this);
        mSearchText.addTextChangedListener(this);
    }


    @Override
    protected void loadData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_search_back:
                finish();
                break;

            default:
                break;
        }
    }

    private void initTabsLayout() {
        mTabs = (SlidingTabLayout) findViewById(R.id.stl_search_tabs);
        mTabViewPager = (ViewPager) findViewById(R.id.vp_search_content);

        mSearchTabsPagerAdapter = new SearchTabPagerAdapter(((AppCompatActivity) mContext).getSupportFragmentManager(), mContext);
        mTabViewPager.setAdapter(mSearchTabsPagerAdapter);
        mTabs.setViewPager(mTabViewPager);
        mTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.WHITE;
            }

            @Override
            public int getDividerColor(int position) {
                return 0;
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        mSearchTabsPagerAdapter.search(mSearchText.getText().toString());
    }
}
