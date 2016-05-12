package com.ketangpai.presenter;

import android.content.Context;
import android.util.Log;

import com.ketangpai.callback.AttachmentResultCallback;
import com.ketangpai.base.BasePresenter;
import com.ketangpai.bean.Data;
import com.ketangpai.bean.Notice;
import com.ketangpai.fragment.AddNoticeFragment;
import com.ketangpai.model.FileModel;
import com.ketangpai.model.NoticeModel;
import com.ketangpai.model.NotificationModel;
import com.ketangpai.modelImpl.FileModelImpl;
import com.ketangpai.modelImpl.NoticeModelImpl;
import com.ketangpai.modelImpl.NotificationModelImpl;
import com.ketangpai.utils.FileUtils;
import com.ketangpai.viewInterface.AddNoticeViewInterface;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by nan on 2016/4/22.
 */
public class AddNoticePresenter extends BasePresenter<AddNoticeViewInterface> {

    NoticeModel noticeModel;
    FileModel fileModel;
    NotificationModel notificationModel;
    AddNoticeViewInterface addNoticeViewInterface;

    public AddNoticePresenter() {
        notificationModel = new NotificationModelImpl();
        noticeModel = new NoticeModelImpl();
        fileModel = new FileModelImpl();
    }

    public void uploadAttachment(final Context context, final BmobFile file) {
        if (isViewAttached()) {
            addNoticeViewInterface = getView();
            fileModel.uploadAttachment(context, file, new AttachmentResultCallback() {
                @Override
                public void onSuccess(BmobFile bmobFile) {
                    Data data = new Data();
                    data.setUrl(bmobFile.getFileUrl(context));
                    data.setSize(FileUtils.getFileSize(file.getLocalFile().length()));
                    data.setName(file.getFilename());
                    addNoticeViewInterface.uploadAttachmentOnComplete(data);
                }

                @Override
                public void onProgress(Integer value) {
                    addNoticeViewInterface.onProgress(value);
                }

                @Override
                public void onFailure(String e) {
                    Log.i(AddNoticeFragment.TAG, e);
                }
            });
        } else {
            Log.i(AddNoticeFragment.TAG, "addAttachment 没有连接到view");
        }
    }

    public void publishNotice(final Context context, final Notice notice, final int c_id, final String c_name) {
        if (isViewAttached()) {
            addNoticeViewInterface = getView();
            noticeModel.publishNotice(context, notice, new SaveListener() {
                @Override
                public void onSuccess() {

                    BmobQuery<Notice> query = new BmobQuery<Notice>();
                    query.getObject(context, notice.getObjectId(), new GetListener<Notice>() {
                        @Override
                        public void onSuccess(Notice notice) {
                            notificationModel.publishStudentNofication(context, notice, c_id, c_name);
                        }

                        @Override
                        public void onFailure(int i, String s) {

                        }
                    });
                    addNoticeViewInterface.publishNoticeOnComplete(notice);
                }

                @Override
                public void onFailure(int i, String s) {
                    Log.i(AddNoticeFragment.TAG, s);
                }
            });
        } else {
            Log.i(AddNoticeFragment.TAG, "publishNotice 没有连接到view");
        }
    }

    ;

}
