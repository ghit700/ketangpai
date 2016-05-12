package com.ketangpai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ketangpai.bean.User;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.utils.ImageLoaderUtils;

import java.util.ArrayList;


/**
 * Created by nan on 2016/3/18.
 */
public class ContactsExAdapter extends BaseExpandableListAdapter {

    Context mContext;
    ArrayList<String> mMessageGroups;
    ArrayList<ArrayList<User>> mMessagmeGroupUsers;

    public ContactsExAdapter(Context mContext, ArrayList<String> mMessageGroups, ArrayList<ArrayList<User>> mMessagmeGroupUsers) {
        this.mContext = mContext;
        this.mMessageGroups = mMessageGroups;
        this.mMessagmeGroupUsers = mMessagmeGroupUsers;
    }


    @Override
    public int getGroupCount() {
        return mMessageGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mMessagmeGroupUsers.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mMessageGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return mMessagmeGroupUsers.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {

        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup viewHolderGroup;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_message_group, parent, false);
            viewHolderGroup = new ViewHolderGroup(convertView);
            convertView.setTag(viewHolderGroup);
        } else {
            viewHolderGroup = (ViewHolderGroup) convertView.getTag();
        }

        viewHolderGroup.groupName.setText(mMessageGroups.get(groupPosition));


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderGroupUser viewHolderGroupUser;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_message_group_item, parent, false);
            viewHolderGroupUser = new ViewHolderGroupUser(convertView);
            convertView.setTag(viewHolderGroupUser);
        } else {
            viewHolderGroupUser = (ViewHolderGroupUser) convertView.getTag();
        }

        ImageLoaderUtils.display(mContext, viewHolderGroupUser.itemUserIcon, mMessagmeGroupUsers.get(groupPosition).get(childPosition).getPath());
        viewHolderGroupUser.itemUserName.setText(mMessagmeGroupUsers.get(groupPosition).get(childPosition).getName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHolderGroup {
        private TextView groupName;

        public ViewHolderGroup(View view) {
            this.groupName = (TextView) view.findViewById(R.id.tv_group_name);
        }
    }

    class ViewHolderGroupUser {
        private ImageView itemUserIcon;
        private TextView itemUserName;

        public ViewHolderGroupUser(View view) {
            itemUserIcon = (ImageView) view.findViewById(R.id.img_message_itemUserIcon);
            itemUserName = (TextView) view.findViewById(R.id.tv_messagme_itemUserName);
        }
    }


}
