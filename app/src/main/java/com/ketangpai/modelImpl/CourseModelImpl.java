package com.ketangpai.modelImpl;

import android.content.Context;
import android.util.Log;

import com.ketangpai.bean.Data;
import com.ketangpai.bean.Notice;
import com.ketangpai.bean.Student_Homework;
import com.ketangpai.bean.Student_Reply;
import com.ketangpai.bean.Teacher_Homework;
import com.ketangpai.bean.Test;
import com.ketangpai.bean.User_Group;
import com.ketangpai.callback.ResultCallback;
import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.bean.Student_Course;
import com.ketangpai.bean.Teacher_Course;
import com.ketangpai.fragment.MainCourseFragment;
import com.ketangpai.model.CourseModel;

import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/4/19.
 */
public class CourseModelImpl implements CourseModel {


    @Override
    public void queryCourseList(Context context, String account, final ResultsCallback resultsCallback) {
        String sql = null;
        sql = "select * from Teacher_Course where account=?";
        Log.i(MainCourseFragment.TAG, "queryCourseList account=" + account + " sql=" + sql);
        BmobQuery<Teacher_Course> query = new BmobQuery<Teacher_Course>();
        query.doSQLQuery(context, sql, new SQLQueryListener<Teacher_Course>() {
            @Override
            public void done(BmobQueryResult<Teacher_Course> bmobQueryResult, BmobException e) {
                List<Teacher_Course> list = bmobQueryResult.getResults();
                if (null != list) {
                    resultsCallback.onSuccess(list);
                } else {
                    resultsCallback.onFailure(e);
                }
            }
        }, account);
    }

    @Override
    public void createCourse(final Context context, final Teacher_Course course, final String path, final ResultCallback resultCallback) {
        Log.i(MainCourseFragment.TAG, "createCourse account=" + course.getAccount() + " id=" + course.getObjectId());
        course.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                BmobQuery<Teacher_Course> query = new BmobQuery<Teacher_Course>();
                query.getObject(context, course.getObjectId(), new GetListener<Teacher_Course>() {
                    @Override
                    public void onSuccess(Teacher_Course o) {
                        User_Group user_group = new User_Group();
                        user_group.setC_id(o.getC_id());
                        user_group.setC_name(o.getName());
                        user_group.setAccount(o.getAccount());
                        user_group.setName(o.getT_name());
                        user_group.setPath(path);
                        user_group.save(context);
                        resultCallback.onSuccess(o);
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        resultCallback.onFailure(s);
                    }
                });
            }

            @Override
            public void onFailure(int i, String s) {
                resultCallback.onFailure(s);
            }
        });
    }


    @Override
    public void deleteCourse(final Context context, Integer c_id) {
        BmobQuery query = new BmobQuery<Teacher_Course>();
        query.addWhereEqualTo("c_id", c_id);
        query.findObjects(context, new FindListener<Teacher_Course>() {
            @Override
            public void onSuccess(List<Teacher_Course> list) {
                if (null != list && list.size() > 0) {
                    for (Teacher_Course course : list) {
                        course.delete(context);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
        query = new BmobQuery<Student_Course>();
        query.findObjects(context, new FindListener<Student_Course>() {
            @Override
            public void onSuccess(List<Student_Course> list) {
                if (null != list && list.size() > 0) {
                    for (Student_Course course : list) {
                        course.delete(context);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
        query = new BmobQuery<Data>();
        query.findObjects(context, new FindListener<Data>() {
            @Override
            public void onSuccess(List<Data> list) {
                if (null != list && list.size() > 0) {
                    for (Data data : list) {
                        data.delete(context);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
        query = new BmobQuery<Notice>();
        query.findObjects(context, new FindListener<Notice>() {
            @Override
            public void onSuccess(List<Notice> list) {
                if (null != list && list.size() > 0) {
                    for (Notice data : list) {
                        data.delete(context);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
        query = new BmobQuery<Teacher_Homework>();
        query.findObjects(context, new FindListener<Teacher_Homework>() {
            @Override
            public void onSuccess(List<Teacher_Homework> list) {
                if (null != list && list.size() > 0) {
                    for (Teacher_Homework data : list) {
                        data.delete(context);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
        query = new BmobQuery<Student_Homework>();
        query.findObjects(context, new FindListener<Student_Homework>() {
            @Override
            public void onSuccess(List<Student_Homework> list) {
                if (null != list && list.size() > 0) {
                    for (Student_Homework data : list) {
                        data.delete(context);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
        query = new BmobQuery<User_Group>();
        query.findObjects(context, new FindListener<User_Group>() {
            @Override
            public void onSuccess(List<User_Group> list) {
                if (null != list && list.size() > 0) {
                    for (User_Group data : list) {
                        data.delete(context);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
        query = new BmobQuery<Test>();
        query.findObjects(context, new FindListener<Test>() {
            @Override
            public void onSuccess(List<Test> list) {
                if (null != list && list.size() > 0) {
                    for (Test data : list) {
                        data.delete(context);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
        query = new BmobQuery<Student_Reply>();
        query.findObjects(context, new FindListener<Student_Reply>() {
            @Override
            public void onSuccess(List<Student_Reply> list) {
                if (null != list && list.size() > 0) {
                    for (Student_Reply data : list) {
                        data.delete(context);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });


    }

//    @Override
//    public void addCourse(final Context context, final String code, final String account, final String name, final long number, final String path) {
//        String sql = "select * from Teacher_Course where code=?";
//        BmobQuery<Teacher_Course> query = new BmobQuery<Teacher_Course>();
//        query.doSQLQuery(context, sql, new SQLQueryListener<Teacher_Course>() {
//
//            @Override
//            public void done(BmobQueryResult<Teacher_Course> bmobQueryResult, BmobException e) {
//                List<Teacher_Course> list = bmobQueryResult.getResults();
//                if (null != list) {
//                    if (list.size() > 0) {
//                        Teacher_Course teacher_course = list.get(0);
//
//                        int numbers = teacher_course.getNumbers();
//                        teacher_course.setNumbers(++numbers);
//                        teacher_course.update(context);
//
//                        final Student_Course course = new Student_Course();
//                        course.setC_id(teacher_course.getC_id());
//                        course.setName(teacher_course.getName());
//                        course.setAccount(account);
//                        course.setTeacher(teacher_course.getT_name());
//                        course.setCode(code);
//                        course.setStudent_name(name);
//                        course.setStudent_number(number);
//                        course.setAdd_time(System.currentTimeMillis());
//                        course.save(context, new SaveListener() {
//                            @Override
//                            public void onSuccess() {
//                                User_Group user_group = new User_Group();
//                                user_group.setC_name(course.getName());
//                                user_group.setC_id(course.getC_id());
//                                user_group.setName(course.getStudent_name());
//                                user_group.setAccount(account);
//                                user_group.setPath(path);
//                                user_group.save(context);
//                            }
//
//                            @Override
//                            public void onFailure(int i, String s) {
//                            }
//                        });
//                    }
//                }
//            }
//        }, code);
//    }


}
