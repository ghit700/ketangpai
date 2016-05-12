package com.ketangpai.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ketangpai.nan.ketangpai.R;

/**
 * Created by Administrator on 2016/4/18.
 */
public abstract class BasePresenterFragment<V, T extends BasePresenter<V>> extends BaseFragment {
    protected T mPresenter;
    private Dialog mLoadingDialog;
    private TextView tv_loading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
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
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }


    protected abstract T createPresenter();

    public void showLoadingDialog() {
        mLoadingDialog = new AlertDialog.Builder(mContext).show();
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_loading, null);
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
