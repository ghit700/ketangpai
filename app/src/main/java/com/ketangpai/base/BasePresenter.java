package com.ketangpai.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by nan on 2016/4/16.
 */
public abstract class BasePresenter<T> {
    protected Reference<T> mViewRef;



    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    protected T getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
