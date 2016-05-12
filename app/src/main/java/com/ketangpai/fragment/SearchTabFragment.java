package com.ketangpai.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ketangpai.activity.ExamActivity;
import com.ketangpai.activity.HomeWorkActivity;
import com.ketangpai.activity.NoticeActivity;
import com.ketangpai.adapter.SearchTwoLineAdapter;
import com.ketangpai.base.BaseAdapter;
import com.ketangpai.base.BasePresenterFragment;
import com.ketangpai.bean.Notice;
import com.ketangpai.bean.Search;
import com.ketangpai.bean.Teacher_Homework;
import com.ketangpai.bean.Test;
import com.ketangpai.listener.OnItemClickListener;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.presenter.SearchPresenter;
import com.ketangpai.viewInterface.SearchViewInterface;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by nan on 2016/3/21.
 */
public class SearchTabFragment extends BasePresenterFragment<SearchViewInterface, SearchPresenter> implements SearchViewInterface, SwipeRefreshLayout.OnRefreshListener, OnItemClickListener {

    //view
    private RecyclerView mSearchTabList;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    //adpter
    private BaseAdapter mTabAdapter;

    //变量
    private List<Search> mTabContents;
    private int position;
    private String mContent;

    public void setPosition(int position) {
        this.position = position;
    }

    public static SearchTabFragment newInstance(int position) {
        SearchTabFragment fragment = new SearchTabFragment();
        fragment.setPosition(position);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_tab;
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fresh_search_tab);
        initSearchTabList();
    }

    @Override
    protected void initData() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

    }

    @Override
    protected void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mTabAdapter.setOnItemClickListener(this);

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    private void initSearchTabList() {
        mSearchTabList = (RecyclerView) view.findViewById(R.id.list_search_tab);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mSearchTabList.setLayoutManager(linearLayoutManager);
        mTabContents = new ArrayList();
        changeTabAdaterByPosition(position, mContext);
    }


    public void changeTabAdaterByPosition(int position, Context context) {
        switch (position) {
            case 0:
                mTabAdapter = new SearchTwoLineAdapter(context, mTabContents);
                break;
            case 1:
                mTabAdapter = new SearchTwoLineAdapter(context, mTabContents);
                break;
            case 2:
                mTabAdapter = new SearchTwoLineAdapter(context, mTabContents);
                break;

            default:
                break;
        }
        mSearchTabList.setAdapter(mTabAdapter);

    }

    public void search(String content) {
        if (content.equals("")) {
            if (null != mSwipeRefreshLayout) {
                mSwipeRefreshLayout.setRefreshing(false);
                mTabContents.clear();
                mTabAdapter.notifyDataSetChanged();
            }
        } else {
            if (null != mSwipeRefreshLayout) {
                mSwipeRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(true);
                    }
                });
                mContent = content;
                onRefresh();
            }
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getSearchList(mContext, position, mContent);
    }

    @Override
    public void loadSearchListOnComplete(List<Search> searchList) {
        mSwipeRefreshLayout.setRefreshing(false);
        mTabContents.clear();
        mTabContents.addAll(searchList);
        mTabAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        Search search = mTabContents.get(position);

        switch (search.getType()) {
            case 1:
                BmobQuery<Teacher_Homework> query = new BmobQuery<>();
                query.addWhereEqualTo("h_id", search.getType_id());
                query.findObjects(mContext, new FindListener<Teacher_Homework>() {
                    @Override
                    public void onSuccess(List<Teacher_Homework> list) {
                        Teacher_Homework homework = list.get(0);
                        Intent intent = new Intent(mContext, HomeWorkActivity.class);
                        intent.putExtra("homework", homework);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(int i, String s) {

                    }
                });
                break;
            case 2:
                BmobQuery<Notice> query1 = new BmobQuery<>();
                query1.addWhereEqualTo("n_id", search.getType_id());
                query1.findObjects(mContext, new FindListener<Notice>() {
                    @Override
                    public void onSuccess(List<Notice> list) {
                        Notice notice = list.get(0);
                        Intent intent = new Intent(mContext, NoticeActivity.class);
                        intent.putExtra("notice", notice);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(int i, String s) {

                    }
                });
                break;
            case 3:
                BmobQuery<Test> query2 = new BmobQuery<>();
                query2.addWhereEqualTo("t_id", search.getType_id());
                query2.findObjects(mContext, new FindListener<Test>() {
                    @Override
                    public void onSuccess(List<Test> list) {
                        Test test = list.get(0);
                        Intent intent = new Intent(mContext, ExamActivity.class);
                        intent.putExtra("test", test);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(int i, String s) {

                    }
                });
                break;

            default:
                break;
        }
    }
}
