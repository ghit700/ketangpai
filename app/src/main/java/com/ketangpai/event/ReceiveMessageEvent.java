package com.ketangpai.event;

import com.ketangpai.bean.MessageInfo;

/**
 * Created by nan on 2016/5/3.
 */
public class ReceiveMessageEvent {
    private MessageInfo messageInfo;

    public ReceiveMessageEvent(MessageInfo messageInfo) {
        this.messageInfo = messageInfo;
    }

    public MessageInfo getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(MessageInfo messageInfo) {
        this.messageInfo = messageInfo;
    }
}
