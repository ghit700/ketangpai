package com.ketangpai.presenter;

import android.content.Context;
import android.util.Log;

import com.ketangpai.bean.Student;
import com.ketangpai.callback.ResultCallback;
import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.base.BasePresenter;
import com.ketangpai.bean.Teacher_Course;
import com.ketangpai.fragment.MainCourseFragment;
import com.ketangpai.model.CourseModel;
import com.ketangpai.model.UserModel;
import com.ketangpai.modelImpl.CourseModelImpl;
import com.ketangpai.modelImpl.UserModelImpl;
import com.ketangpai.viewInterface.MainCourseViewInterface;

import java.util.List;

import cn.bmob.v3.exception.BmobException;

/**
 * Created by Administrator on 2016/4/19.
 */
public class MainCoursePresenter extends BasePresenter<MainCourseViewInterface> {
    private CourseModel courseModel;
    private MainCourseViewInterface mainCourseViewInterface;
    private UserModel userModel;

    public MainCoursePresenter() {
        courseModel = new CourseModelImpl();
        userModel = new UserModelImpl();
    }

    public void getCourseList(Context context, String account) {
        if (isViewAttached()) {
            mainCourseViewInterface = getView();
            courseModel.queryCourseList(context, account, new ResultsCallback() {
                @Override
                public void onSuccess(List list) {
                    if (list.size() > 0) {
                        mainCourseViewInterface.getCourseListOnComplete(list);
                    } else {
                        mainCourseViewInterface.getCourseListOnComplete(null);
                    }
                }

                @Override
                public void onFailure(BmobException e) {
                    Log.i(MainCourseFragment.TAG, "getCourseList " + e.getMessage());
                }
            });
        } else {
            Log.i(MainCourseFragment.TAG, "没有连接到view");
        }

    }

    public void createCourse(Context context, final Teacher_Course course, String path) {
        if (isViewAttached()) {
            mainCourseViewInterface = getView();
            mainCourseViewInterface.showLoading();

            courseModel.createCourse(context, course, path, new ResultCallback() {
                @Override
                public void onSuccess(Object object) {
                    mainCourseViewInterface.createCourseOnComplete((Teacher_Course) object);
                    mainCourseViewInterface.hideLoading();
                }

                @Override
                public void onFailure(String e) {
                    Log.i(MainCourseFragment.TAG, "createCourse " + e);
                    mainCourseViewInterface.createCourseOnComplete(null);
                    mainCourseViewInterface.hideLoading();
                }
            });


        } else {
            Log.i(MainCourseFragment.TAG, "没有连接到view");
        }
    }


    public void deleteCourse(Context mContext, int c_id) {
        if (isViewAttached()) {
            mainCourseViewInterface = getView();
            courseModel.deleteCourse(mContext, c_id);

        }
    }

//    public void addStudentToCourse(Context mContext, List<Student> mStudentList, int c_id) {
//        if (isViewAttached()) {
//            mainCourseViewInterface = getView();
//
//        }
//    }

//    public void getStudentList(Context mContext, int c_id,String account) {
//        if (isViewAttached()) {
//            mainCourseViewInterface = getView();
//            userModel.getStudentList(mContext,c_id,account ,new ResultsCallback() {
//                @Override
//                public void onSuccess(List list) {
//                    mainCourseViewInterface.getStudentListOnComplete(list);
//                }
//
//                @Override
//                public void onFailure(BmobException e) {
//
//                }
//            });
//        }
//    }
}
