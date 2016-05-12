package com.ketangpai.viewInterface;

import com.ketangpai.bean.Data;
import com.ketangpai.bean.Notice;

import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by nan on 2016/4/22.
 */
public interface AddNoticeViewInterface {
    /**
     * 上传附件完成
     */
    void uploadAttachmentOnComplete(Data data);

    /**
     * 显示文件上传的百分比
     *
     * @param value
     */
    void onProgress(int value);

    /**
     * 发布公告
     */
    void publishNoticeOnComplete(Notice notice);

}
