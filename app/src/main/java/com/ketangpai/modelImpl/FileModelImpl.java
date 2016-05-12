package com.ketangpai.modelImpl;

import android.content.Context;
import android.util.Log;

import com.ketangpai.callback.AttachmentResultCallback;
import com.ketangpai.constant.Constant;
import com.ketangpai.fragment.AddNoticeFragment;
import com.ketangpai.fragment.DataFragment;
import com.ketangpai.model.FileModel;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by nan on 2016/4/24.
 */
public class FileModelImpl implements FileModel {
    @Override
    public void uploadAttachment(final Context context, final BmobFile bmobFile, final AttachmentResultCallback resultCallback) {
        Log.i(AddNoticeFragment.TAG, "uploadAttachment fileName=" + bmobFile.getFilename());

        bmobFile.upload(context, new UploadFileListener() {
            @Override
            public void onSuccess() {

                resultCallback.onSuccess(bmobFile);
            }


            @Override
            public void onProgress(Integer value) {
                super.onProgress(value);
                resultCallback.onProgress(value);
            }

            @Override
            public void onFailure(int i, String s) {
                resultCallback.onFailure(s);
            }
        });
    }

    @Override
    public void downloadData(Context context, String url, String fileName, DownloadFileListener resultCallback) {
        Log.i(DataFragment.TAG, "downloadData url=" + url + " filename=" + fileName + " save=" + Constant.ALBUM_PATH + Constant.DATA_FOLDER);
        File file = new File(Constant.ALBUM_PATH + Constant.DATA_FOLDER, fileName);
        if (!file.exists()) {
            BmobFile bmobFile = new BmobFile(fileName, "", url);
            bmobFile.download(context, file, resultCallback);
        } else {
            resultCallback.onSuccess(file.getAbsolutePath());
        }
    }


}
