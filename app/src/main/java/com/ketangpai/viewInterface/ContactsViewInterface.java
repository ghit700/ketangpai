package com.ketangpai.viewInterface;

import com.ketangpai.bean.User_Group;

import java.util.List;

/**
 * Created by nan on 2016/5/2.
 */
public interface ContactsViewInterface {
    void getContactListOnComplete(List<User_Group> user_groups);
}
