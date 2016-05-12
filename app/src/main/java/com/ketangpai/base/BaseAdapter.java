package com.ketangpai.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ketangpai.listener.OnItemClickListener;
import com.ketangpai.nan.ketangpai.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nan on 2016/3/15.
 */
public abstract class BaseAdapter<E> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    protected final Context mContext;
    protected final List<E> mDataList;
    protected LayoutInflater mLayoutInflater;
    protected TypedValue typedValue;

    public BaseAdapter(Context mContext, List<E> mDataList) {
        this.mContext = mContext;
        this.mDataList = (mDataList != null) ? mDataList : new ArrayList<E>();
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        view = mLayoutInflater.inflate(getItemLayoutId(viewType), parent, false);
        typedValue = new TypedValue();
        mContext.getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);

        return new ViewHolder(view);
    }

    protected abstract int getItemLayoutId(int viewType);


    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder holder, int position) {

        bindData(holder, position, mDataList.get(position));

    }

    protected  void bindData(ViewHolder holder, int position, E item){

    };

    @Override
    public int getItemCount() {
        return (mDataList != null) ? mDataList.size() : 0;
    }

    ;

    public void addItem(int positon, E item) {
        mDataList.add(positon, item);
        notifyItemInserted(positon);
    }

    ;

    public void deleteItem(int positon) {
        mDataList.remove(positon);
//        notifyItemRemoved(positon);
        notifyDataSetChanged();
    }

    public void updateItem(int position) {
        notifyItemChanged(position);
    }

    public void clearData() {
        mDataList.clear();
//        notifyDataSetChanged();
    }

    ;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View
            .OnLongClickListener {

        private SparseArray<View> mViews;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mViews = new SparseArray<View>();
        }

        public View getViewById(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }


        @Override
        public void onClick(View v) {
            if (getOnItemClickListener() != null) {
                getOnItemClickListener().onItemClick(v, getPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
