package com.ketangpai.model;

import android.content.Context;

import com.ketangpai.callback.AttachmentResultCallback;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.DownloadFileListener;

/**
 * Created by nan on 2016/4/24.
 */
public interface FileModel {
    void uploadAttachment(Context context, BmobFile file, AttachmentResultCallback resultCallback);

    void downloadData(Context context, String url, String fileName, DownloadFileListener resultCallback);
}
