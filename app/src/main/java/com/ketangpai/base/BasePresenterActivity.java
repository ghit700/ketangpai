package com.ketangpai.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.ketangpai.nan.ketangpai.R;



/**
 * Created by nan on 2016/4/16.
 */
public abstract class BasePresenterActivity<V, T extends BasePresenter<V>> extends BaseActivity {
    protected T mPresenter;
    private Dialog mLoadingDialog;
    private TextView tv_loading;

    @Override
    protected int getContentViewId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected abstract T createPresenter();

    public void showLoadingDialog() {
        mLoadingDialog = new AlertDialog.Builder(mContext).show();
        View view = getLayoutInflater().inflate(R.layout.dialog_loading, null);
        tv_loading = (TextView) view.findViewById(R.id.tv_loading);
        mLoadingDialog.setContentView(view);
        mLoadingDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * 修改载入过程的文字
     *
     * @param text
     */
    public void setLoadingText(String text) {
        tv_loading.setText(text);
    }


    public void dismissLoadingDialog() {
        mLoadingDialog.dismiss();
    }
}
