package com.ketangpai.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;

import com.ketangpai.activity.ChatActivity;
import com.ketangpai.adapter.ContactsExAdapter;
import com.ketangpai.base.BasePresenterFragment;
import com.ketangpai.bean.User;
import com.ketangpai.bean.User_Group;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.presenter.ContactsPresenter;
import com.ketangpai.utils.NetUtils;
import com.ketangpai.viewInterface.ContactsViewInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nan on 2016/3/15.
 */
public class ContactsFragment extends BasePresenterFragment<ContactsViewInterface, ContactsPresenter> implements ContactsViewInterface, ExpandableListView.OnChildClickListener {

    //view
    ExpandableListView mMessageExList;

    //adpter
    ContactsExAdapter mContactsExAdapter;

    //变量
    List<User_Group> mConstacts;
    ArrayList<String> mGroupUsers;
    ArrayList<ArrayList<User>> mGroupItemUsers;
    private String account;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contacts;
    }

    @Override
    protected void initVarious() {
        super.initVarious();
        account = mContext.getSharedPreferences("user", 0).getString("account", "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected ContactsPresenter createPresenter() {
        return new ContactsPresenter();
    }

    @Override
    protected void initView() {
        mMessageExList = (ExpandableListView) view.findViewById(R.id.exlist_messagme);
        initMessageExList();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mMessageExList.setOnChildClickListener(this);
    }

    @Override
    protected void loadData() {
        if (NetUtils.hasNetworkConnection()) {
            mPresenter.getConstactList(mContext, account);
        } else {
            sendToast("没有网络连接");
        }
    }

    private void initMessageExList() {
        mGroupUsers = new ArrayList<>();
        mGroupItemUsers = new ArrayList<>();
        mContactsExAdapter = new ContactsExAdapter(mContext, mGroupUsers, mGroupItemUsers);
        mMessageExList.setAdapter(mContactsExAdapter);

    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Intent intent = new Intent(mContext, ChatActivity.class);
        intent.putExtra("send_user", mGroupItemUsers.get(groupPosition).get(childPosition));
        startActivity(intent);
        return true;
    }


    @Override
    public void getContactListOnComplete(List<User_Group> user_groups) {
        mConstacts = user_groups;
        getUsersGroupByCourse(mConstacts);
        mContactsExAdapter.notifyDataSetChanged();
    }


    /**
     * 通讯录数组处理
     *
     * @param users
     */
    public void getUsersGroupByCourse(List<User_Group> users) {


        int preC_id = users.get(0).getC_id();
        int i = 0;

        mGroupUsers.add(users.get(0).getC_name());
        mGroupItemUsers.add(new ArrayList<User>());
        for (User_Group user_group : users) {
            int nextC_id = user_group.getC_id();

            if (preC_id == nextC_id) {

                mGroupItemUsers.get(i).add(new User(user_group.getAccount(), user_group.getName(), user_group.getPath()));
            } else {
                preC_id = nextC_id;
                i++;
                mGroupUsers.add(user_group.getC_name());
                mGroupItemUsers.add(new ArrayList<User>());
                mGroupItemUsers.get(i).add(new User(user_group.getAccount(), user_group.getName(), user_group.getPath()));
            }
        }


    }

}
