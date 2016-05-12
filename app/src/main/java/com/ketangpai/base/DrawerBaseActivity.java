package com.ketangpai.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.ketangpai.nan.ketangpai.R;

import com.ketangpai.activity.AccountActivity;
import com.ketangpai.activity.LoginActivity;
import com.ketangpai.activity.MainActivity;
import com.ketangpai.constant.Constant;
import com.ketangpai.fragment.AccountFragment;
import com.ketangpai.utils.ImageLoaderUtils;

import java.io.File;
import java.util.List;


/**
 * Created by nan on 2016/3/15.
 */
public abstract class DrawerBaseActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {

    //导航界面
    protected FrameLayout mfl_main_drawerContent;
    protected DrawerLayout mDrawerContainer;
    //view
    protected Toolbar mToolbar;
    protected ImageView mExitLoginImg;
    private ImageView mUserIconImg;
    protected TextView mDrawerCourseText, mDrawerMessageText, mUserNameText, mDrawerContactsText;


    //变量
    //courselist

    protected String name;
    private String account;
    private final int ACCOUNT_REQUEST = 1;
    private String path;

    //主界面打开的类型
    public final static int COURSE = 1;
    public final static int MESSAGE = 2;
    public final static int CONTACTS = 3;
    //notificationlist
    protected List mNotificationContents;


    @Override
    protected void onStop() {
        super.onStop();
        mDrawerContainer.closeDrawer(Gravity.LEFT);
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        name = getSharedPreferences("user", 0).getString("name", "");
        path = getSharedPreferences("user", 0).getString("path", "");
        account = getSharedPreferences("user", 0).getString("account", "");

    }

    @Override
    protected void initView() {
        initToolBar();
        initDrawerContent();
        mUserIconImg = (ImageView) findViewById(R.id.img_main_userIcon);
        mExitLoginImg = (ImageView) findViewById(R.id.img_main_exitLogin);
        mfl_main_drawerContent = (FrameLayout) findViewById(R.id.fl_main_drawerContent);
        mDrawerContainer = (DrawerLayout) findViewById(R.id.drawer_main_container);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main_container, getLayoutFragment()).commit();
    }

    @Override
    protected void initData() {
        File file = new File(Constant.LOGO_FOLDER);
        if (!file.exists() || path.equals("")) {
            ImageLoaderUtils.display(mContext, mUserIconImg, path);
        } else {

            ImageLoaderUtils.displayNoDisk(mContext, mUserIconImg, Constant.LOGO_FOLDER);
        }

    }

    @Override
    protected void initListener() {
        mDrawerMessageText.setOnClickListener(this);
        mDrawerCourseText.setOnClickListener(this);
        mExitLoginImg.setOnClickListener(this);
        mUserIconImg.setOnClickListener(this);
        mDrawerContactsText.setOnClickListener(this);
        //拦截点击事件，不让在导航界面的点击事件传递到mainContent中
        mfl_main_drawerContent.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_main_drawerCourse:
                startMainActivity(COURSE);
                finish();
                break;
            case R.id.tv_main_drawerMessage:
                startMainActivity(MESSAGE);
                finish();
                break;
            case R.id.img_main_exitLogin:
                showExitDialog();
                break;
            case R.id.img_main_userIcon:
                startAccountActivity();
                break;
        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mDrawerContainer.isDrawerOpen(Gravity.LEFT)) {
            mDrawerContainer.closeDrawer(Gravity.LEFT);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void initDrawerContent() {
        mUserIconImg = (ImageView) findViewById(R.id.img_main_userIcon);
        mExitLoginImg = (ImageView) findViewById(R.id.img_main_exitLogin);
        mUserNameText = (TextView) findViewById(R.id.tv_main_userName);
        mDrawerCourseText = (TextView) findViewById(R.id.tv_main_drawerCourse);
        mDrawerMessageText = (TextView) findViewById(R.id.tv_main_drawerMessage);
        mDrawerContactsText = (TextView) findViewById(R.id.tv_main_contacts);
        mUserNameText.setText(name);
        mDrawerCourseText.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));

    }


    protected void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    protected abstract Fragment getLayoutFragment();


    private void startMainActivity(int type) {
        Intent intent = new Intent(mContext, MainActivity.class);
        if (type == COURSE) {
            intent.putExtra("type", COURSE);
        } else {
            intent.putExtra("type", MESSAGE);
        }
        startActivity(intent);
    }

    ;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    protected void showExitDialog() {
        new AlertDialog.Builder(mContext).setTitle("退出登录").setMessage("是否确认退出登录").setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getSharedPreferences("user", 0).edit().clear().commit();
                getSharedPreferences("user", 0).edit().putString("account", account).commit();
                getSharedPreferences("user", 0).edit().putString("path", path).commit();
                startActivity(new Intent(mContext, LoginActivity.class));
                finish();
            }
        }).create().show();
    }

    ;

    protected void startAccountActivity() {

        Intent intent = new Intent(mContext, AccountActivity.class);
        startActivityForResult(intent, ACCOUNT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACCOUNT_REQUEST && resultCode == AccountFragment.ACCOUNT_RESULT) {
            Log.i("====", "111");
            ImageLoaderUtils.displayNoDisk(mContext, mUserIconImg, Constant.LOGO_FOLDER);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
