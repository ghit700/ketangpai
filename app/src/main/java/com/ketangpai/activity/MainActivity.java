package com.ketangpai.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.ketangpai.nan.ketangpai.R;

import com.ketangpai.base.DrawerBaseActivity;
import com.ketangpai.bean.Installation;
import com.ketangpai.fragment.ContactsFragment;
import com.ketangpai.fragment.MainCourseFragment;
import com.ketangpai.fragment.MessageFragment;
import com.ketangpai.utils.ActivityCollector;

import java.util.List;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;


/**
 * Created by nan on 2016/3/9.
 */
public class MainActivity extends DrawerBaseActivity implements View.OnClickListener, View.OnTouchListener {


    //    view


    //    adapter


    //   变量
    //保存点击的时间
    private long exitTime;
    //actionbar开关对象
    private ActionBarDrawerToggle mDrawerToggle;
    private String account;
    private MainCourseFragment mMainCourseFragment;
    private ContactsFragment mContactsFragment;
    private MessageFragment mMessageFragment;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_base_nevigation;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


    ;

    @Override
    protected void onResume() {
        super.onResume();

        if (getIntent().getIntExtra("type", -1) != -1) {
            int type = getIntent().getIntExtra("type", -1);
            selectNevigationText(type);
        }
    }


    @Override
    protected void initVariables() {
        super.initVariables();
        // 使用推送服务时的初始化操作
        BmobInstallation.getCurrentInstallation(this).save();
        registerAccount();
        // 启动推送服务
        BmobPush.startWork(this);
        account = getSharedPreferences("user", 0).getString("account", "");
        mMainCourseFragment = new MainCourseFragment();
        mContactsFragment = new ContactsFragment();
        mMessageFragment = new MessageFragment();
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();

        initDrawerToggle();
        selectNevigationText(DrawerBaseActivity.COURSE);
    }


    @Override
    protected void initListener() {
        super.initListener();
        mDrawerContainer.setDrawerListener(mDrawerToggle);


    }


    @Override
    protected void loadData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_main_drawerCourse:
                mDrawerContainer.closeDrawer(Gravity.LEFT);
                selectNevigationText(DrawerBaseActivity.COURSE);

                return;

            case R.id.tv_main_drawerMessage:
                mDrawerContainer.closeDrawer(Gravity.LEFT);
                selectNevigationText(DrawerBaseActivity.MESSAGE);
                return;

            case R.id.tv_main_contacts:
                mDrawerContainer.closeDrawer(Gravity.LEFT);
                selectNevigationText(DrawerBaseActivity.CONTACTS);
            default:
                break;
        }
        super.onClick(v);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            mDrawerContainer.post(new Runnable() {
                @Override
                public void run() {
                    supportInvalidateOptionsMenu();
                }
            });
            return true;
        }

        switch (item.getItemId()) {
            case R.id.search:
                startActivity(new Intent(mContext, SearchActivity.class));

                break;


        }
        return true;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //双击退出程序
        if (keyCode == KeyEvent.KEYCODE_BACK && mDrawerContainer.isDrawerOpen(Gravity.LEFT)) {
            mDrawerContainer.closeDrawer(Gravity.LEFT);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                showToast("再按一次退出", 0);
                exitTime = System.currentTimeMillis();
                return true;
            } else {

                ActivityCollector.finishAllActivity();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        getSupportActionBar().setTitle("课堂");

    }


    @Override
    protected Fragment getLayoutFragment() {

        return new MainCourseFragment();
    }

    private void selectNevigationText(int type) {
        if (type == DrawerBaseActivity.COURSE) {
            mDrawerCourseText.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
            mDrawerMessageText.setBackgroundColor(getResources().getColor(android.R.color.white));
            mDrawerContactsText.setBackgroundColor(getResources().getColor(android.R.color.white));
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_container, mMainCourseFragment).commit();
            getSupportActionBar().setTitle("课程");
        } else if (type == DrawerBaseActivity.CONTACTS) {
            mDrawerCourseText.setBackgroundColor(getResources().getColor(R.color.white));
            mDrawerMessageText.setBackgroundColor(getResources().getColor(android.R.color.white));
            mDrawerContactsText.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_container, mContactsFragment).commit();
            getSupportActionBar().setTitle("通讯录");

        } else {
            mDrawerMessageText.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
            mDrawerCourseText.setBackgroundColor(getResources().getColor(android.R.color.white));
            mDrawerContactsText.setBackgroundColor(getResources().getColor(android.R.color.white));
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_container, mMessageFragment).commit();
            getSupportActionBar().setTitle("私信");

        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mDrawerToggle.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        mDrawerToggle.onConfigurationChanged(newConfig);

    }

    private void initDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle((Activity) mContext, mDrawerContainer, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }


            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
                supportInvalidateOptionsMenu();

            }
        };
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean isOpen = mDrawerContainer.isDrawerVisible(mfl_main_drawerContent);
        menu.findItem(R.id.search).setVisible(!isOpen);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void registerAccount() {
        BmobQuery<Installation> bmobQuery = new BmobQuery<Installation>();
        bmobQuery.addWhereEqualTo("installationId", BmobInstallation.getCurrentInstallation(this).getInstallationId());
        bmobQuery.findObjects(this, new FindListener<Installation>() {
            @Override
            public void onSuccess(List<Installation> list) {
                if (null != list) {
                    Installation installation = list.get(0);
                    installation.setAccount(account);
                    installation.update(mContext);
                } else {
                    Log.i("===MainActivity", "registerAccount list null");

                }
            }

            @Override
            public void onError(int i, String s) {
                Log.i("===MainActivity", "registerAccount " + s);
            }
        });
    }


}
