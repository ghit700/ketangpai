package com.ketangpai.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ketangpai.fragment.SearchTabFragment;
import com.ketangpai.nan.ketangpai.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nan on 2016/3/21.
 */
public class SearchTabPagerAdapter extends FragmentPagerAdapter {
    private String[] mSearchTabs;
    private List<SearchTabFragment> mFragments;

    public SearchTabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mSearchTabs = context.getResources().getStringArray(R.array.search_tabs);
        mFragments = new ArrayList<>();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mSearchTabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        mFragments.add(position, SearchTabFragment.newInstance(position));
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mSearchTabs.length;
    }


    public void search(String content) {
        for (SearchTabFragment fragment : mFragments) {
            if (null != fragment) {
                fragment.search(content);
            }
        }
    }
}
