package com.ketangpai.viewInterface;

import com.ketangpai.bean.MessageInfo;
import com.ketangpai.bean.NewestMessage;

import java.util.List;

/**
 * Created by nan on 2016/5/3.
 */
public interface MessageViewInterface {
    void getNewestMessageListOnComplete(List<NewestMessage> newestMessages);


}
