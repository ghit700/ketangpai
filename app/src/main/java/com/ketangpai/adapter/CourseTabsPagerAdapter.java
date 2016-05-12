package com.ketangpai.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ketangpai.bean.Course;
import com.ketangpai.fragment.CourseTabFragment;
import com.ketangpai.nan.ketangpai.R;

/**
 * Created by nan on 2016/3/16.
 */
public class CourseTabsPagerAdapter extends FragmentPagerAdapter {

    private String[] mCourseTabs;
    private Course course;

    public CourseTabsPagerAdapter(FragmentManager fm, Context context, Course course) {
        super(fm);
        mCourseTabs = context.getResources().getStringArray(R.array.course_tabs);
        this.course = course;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mCourseTabs[position];
    }


    @Override
    public int getCount() {
        return mCourseTabs.length;
    }


    @Override
    public Fragment getItem(int position) {

        return CourseTabFragment.newInstance(position,course);
    }


}
