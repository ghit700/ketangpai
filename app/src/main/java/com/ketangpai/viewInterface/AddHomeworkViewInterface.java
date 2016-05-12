package com.ketangpai.viewInterface;

import com.ketangpai.bean.Data;
import com.ketangpai.bean.Teacher_Homework;

/**
 * Created by nan on 2016/4/22.
 */
public interface AddHomeworkViewInterface {
    void addHomeWorkOnComplete(Teacher_Homework homework);

    void uploadAttachmentOnComplete(Data data);

    /**
     * 显示文件上传的百分比
     *
     * @param value
     */
    void onProgress(int value);
}
