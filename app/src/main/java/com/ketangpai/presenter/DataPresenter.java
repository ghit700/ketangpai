package com.ketangpai.presenter;

import android.content.Context;
import android.util.Log;

import com.ketangpai.base.BasePresenter;
import com.ketangpai.fragment.DataFragment;
import com.ketangpai.model.FileModel;
import com.ketangpai.modelImpl.FileModelImpl;
import com.ketangpai.viewInterface.DataViewInterface;

import java.io.File;

import cn.bmob.v3.listener.DownloadFileListener;

/**
 * Created by nan on 2016/4/24.
 */
public class DataPresenter extends BasePresenter<DataViewInterface> {
    DataViewInterface mDataViewInterface;
    FileModel fileModel;

    public DataPresenter() {
        this.fileModel = new FileModelImpl();
    }

    public void downloadData(Context context, String url, String fileName) {
        if (isViewAttached()) {
            mDataViewInterface = getView();
            fileModel.downloadData(context, url, fileName, new DownloadFileListener() {
                @Override
                public void onSuccess(String s) {

                    File file = new File(s);
                    mDataViewInterface.downloadOnComplete(file);
                }

                @Override
                public void onProgress(Integer progress, long total) {
                    super.onProgress(progress, total);
                    mDataViewInterface.onProgress(progress, total);
                }

                @Override
                public void onFailure(int i, String s) {
                    Log.i(DataFragment.TAG, "downloadData " + s);
                }
            });
        }
    }
}
