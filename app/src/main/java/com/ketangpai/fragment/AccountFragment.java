package com.ketangpai.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ketangpai.activity.AccountUpdateActivity;
import com.ketangpai.base.BasePresenterFragment;
import com.ketangpai.bean.User;
import com.ketangpai.constant.Constant;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.presenter.AccountPresenter;
import com.ketangpai.utils.ImageLoaderUtils;
import com.ketangpai.utils.IntentUtils;
import com.ketangpai.view.ActionSheetDialog;
import com.ketangpai.viewInterface.AccountViewInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by nan on 2016/3/21.
 */
public class AccountFragment extends BasePresenterFragment<AccountViewInterface, AccountPresenter> implements View.OnClickListener, AccountViewInterface {

    public static final String TAG = "===AccountFragment";
    RelativeLayout mUserIcon, mName, mShool, mPassword;
    TextView tv_account, tv_school, tv_name, tv_number;
    ImageView img_account_user;
    //变量
    private String school;
    private String name;
    private String account;
    private String u_id;
    private String path;
    private int UPDATE_REQUEST = 100;
    private AccountFragment mFragment;
    private String File_Path;
    public static final int ACCOUNT_RESULT = 12;

    @Override
    protected void initVarious() {
        super.initVarious();

        school = mContext.getSharedPreferences("user", 0).getString("school", "");
        name = mContext.getSharedPreferences("user", 0).getString("name", "");
        account = mContext.getSharedPreferences("user", 0).getString("account", "");
        u_id = mContext.getSharedPreferences("user", 0).getString("u_id", "");
        path = mContext.getSharedPreferences("user", 0).getString("path", "");
        mFragment = this;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_account;
    }

    @Override
    protected void initView() {
        mUserIcon = (RelativeLayout) view.findViewById(R.id.ll_account_userIcon);
        mShool = (RelativeLayout) view.findViewById(R.id.ll_account_school);
        mName = (RelativeLayout) view.findViewById(R.id.ll_account_name);
        mPassword = (RelativeLayout) view.findViewById(R.id.ll_account_password);
        tv_account = (TextView) view.findViewById(R.id.tv_account);
        tv_school = (TextView) view.findViewById(R.id.tv_school);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        img_account_user = (ImageView) view.findViewById(R.id.img_account_user);


    }

    @Override
    protected void initData() {
        tv_account.setText(account);
        tv_school.setText(school);
        tv_name.setText(name);

        File file = new File(Constant.LOGO_FOLDER);
        if (!file.exists() || path.equals("")) {
            ImageLoaderUtils.display(mContext, img_account_user, path);
        } else {
            ImageLoaderUtils.displayNoDisk(mContext, img_account_user, Constant.LOGO_FOLDER);
        }


    }

    @Override
    protected void initListener() {
        mName.setOnClickListener(this);
        mUserIcon.setOnClickListener(this);
        mShool.setOnClickListener(this);
        mPassword.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }


    @Override
    public void onStop() {
        getActivity().setResult(ACCOUNT_RESULT);
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ll_account_name:
                intent = new Intent(mContext, AccountUpdateActivity.class);
                intent.putExtra("columnName", "姓名");
                startActivityForResult(intent, UPDATE_REQUEST);
                break;
            case R.id.ll_account_school:
                intent = new Intent(mContext, AccountUpdateActivity.class);
                intent.putExtra("columnName", "学校");
                startActivityForResult(intent, UPDATE_REQUEST);
                break;
            case R.id.ll_account_userIcon:
                showUpdateHeadDialog("选择图片", "拍照", "从相册中选择");
                break;

            case R.id.ll_account_password:
                intent = new Intent(mContext, AccountUpdateActivity.class);
                intent.putExtra("columnName", "密码");
                startActivityForResult(intent, UPDATE_REQUEST);
                break;

            default:
                break;
        }
    }

    private void showUpdateHeadDialog(String title, String photo, String album) {
        new ActionSheetDialog(mContext)
                .builder()
                .setTitle(title)
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem(photo, ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        File file = new File(Constant.PHOTO_FOLDER);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        File_Path = IntentUtils.openCamera(mFragment);
                    }
                })
                .addSheetItem(album, ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        IntentUtils.openImageFile(mFragment);
                    }
                })
                .show();
    }

    @Override
    public void onActivityResult(int requsetCode, int resultCode, Intent data) {


        if (requsetCode == IntentUtils.OPEN_IMGAE && resultCode == getActivity().RESULT_OK) {
            Uri uri = data.getData();

            Bitmap bitmap = null;
            try {
                FileOutputStream fos = new FileOutputStream(Constant.LOGO_FOLDER, false);
                InputStream is = mContext.getContentResolver().openInputStream(uri);
                bitmap = BitmapFactory.decodeStream(is);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);

            } catch (Exception e) {
                e.printStackTrace();
            }
            ImageLoaderUtils.display(mContext, img_account_user, Constant.LOGO_FOLDER);
            User user = new User();
            user.setObjectId(u_id);
            user.setAccount(account);
            File file = new File(Constant.LOGO_FOLDER);
            mPresenter.uploadUserLogo(mContext, file, user);
        }

        if (requsetCode == IntentUtils.CAMERA_REQUEST && resultCode == getActivity().RESULT_OK) {
            File file = new File(File_Path);
            ImageLoaderUtils.display(mContext, img_account_user, file.getPath());
            User user = new User();
            user.setObjectId(u_id);
            user.setAccount(account);
            mPresenter.uploadUserLogo(mContext, file, user);
        }

        if (requsetCode == UPDATE_REQUEST && resultCode == 100) {
            if (data != null) {
                if (null != data.getStringExtra("columnCode") && null != data.getStringExtra("columnValue")) {
                    String columnCode = data.getStringExtra("columnCode");
                    String columnValue = data.getStringExtra("columnValue");
//                    Log.i(AccountUpdateFragment.TAG, "onActivityResult columnCode=" + columnCode + " columnValue=" + columnValue);
                    switch (columnCode) {
                        case "password":
                            break;

                        case "school":
                            tv_school.setText(columnValue);
                            break;
                        case "number":
                            tv_number.setText(columnValue);
                            break;
                        case "name":
                            tv_name.setText(columnValue);
                            break;
                        default:
                            break;
                    }
                }

            }
        }
        super.onActivityResult(requsetCode, resultCode, data);
    }

    @Override
    public void showUploadLogoDialong() {
        showLoadingDialog();
        setLoadingText("上传头像中...");
    }

    @Override
    public void hideUploadLogoDialong() {
        dismissLoadingDialog();
    }

    @Override
    public void uploadLogoOnComplete(int ret) {
        Log.i(TAG, "uploadLogoOnComplete ret=" + ret);
        if (ret > 0) {

        } else {
            new AlertDialog.Builder(mContext).setTitle("上传头像失败").
                    setPositiveButton("确认", null).show();
        }
    }

    @Override
    protected AccountPresenter createPresenter() {
        return new AccountPresenter();
    }
}


