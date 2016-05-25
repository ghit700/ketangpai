package com.ketangpai.adapter;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.ketangpai.base.BaseAdapter;
import com.ketangpai.bean.Course;
import com.ketangpai.bean.Teacher_Course;
import com.ketangpai.model.CourseModel;
import com.ketangpai.modelImpl.CourseModelImpl;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.utils.CodeUtils;
import com.ketangpai.view.MyPopupMenu;

import java.util.List;

/**
 * Created by nan on 2016/3/14.
 */
public class CourseMainCourseAdapter extends BaseAdapter<Teacher_Course> {


    private CourseItemListener mItemListener;

    public CourseMainCourseAdapter(Context mContext, List<Teacher_Course> mDataList, CourseItemListener listener) {
        super(mContext, mDataList);
        this.mItemListener = listener;
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_main_course;
    }

    @Override
    protected void bindData(ViewHolder holder, final int position, Teacher_Course s) {
        //初始化view
        TextView CourseName = (TextView) holder.getViewById(R.id.tv_item_courseName);
        TextView StudentCount = (TextView) holder.getViewById(R.id.tv_item_StudentCount);
        TextView CourseCode = (TextView) holder.getViewById(R.id.tv_item_courseCode);
        ImageView CourseEdit = (ImageView) holder.getViewById(R.id.img_item_courseMore);

        //设置view的事件
        //CourseCode的点击事件
        final MyPopupMenu mCourseCodePopupMenu = new MyPopupMenu(mContext, CourseCode, R.menu.teacher_courese_code_menu);
        CourseCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCourseCodePopupMenu.show();

            }
        });
        mCourseCodePopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Course course = mDataList.get(position);
                course.setCode(CodeUtils.createCode());
                course.update(mContext);
                notifyItemChanged(position);
                return true;
            }
        });
        //CourseEdit的点击事件
        final MyPopupMenu mCourseEditPopupMenu = new MyPopupMenu(mContext, CourseEdit, R.menu.teacher_courese_edit_menu);
        mCourseEditPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Course course = mDataList.get(position);
                switch (item.getItemId()) {

                    case R.id.item_menu_add_student:
                        mItemListener.addStudent(course.getC_id());
                        break;

                    case R.id.item_menu_delete:
                        mItemListener.deleteCourse(course.getC_id());
                        break;

                    default:
                        break;
                }
                return true;
            }
        });
        CourseEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCourseEditPopupMenu.show();

            }
        });

        //初始化view的值
        CourseName.setText(s.getName());
        CourseCode.setText(s.getCode());
        StudentCount.setText("成员" + String.valueOf(((Teacher_Course) s).getNumbers()) + "人");

    }

    public interface CourseItemListener {
        void deleteCourse(int c_id);

        void addStudent(int c_id);
    }


}
