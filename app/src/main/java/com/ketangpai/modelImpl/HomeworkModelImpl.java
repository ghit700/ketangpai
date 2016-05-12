package com.ketangpai.modelImpl;

import android.content.Context;
import android.util.Log;

import com.ketangpai.bean.Search;
import com.ketangpai.bean.Student_Course;
import com.ketangpai.bean.Student_Homework;
import com.ketangpai.callback.ResultCallback;
import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.bean.Teacher_Homework;
import com.ketangpai.fragment.AddHomeworkFragment;
import com.ketangpai.fragment.HomeworkFragment;
import com.ketangpai.model.HomeworkModel;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by nan on 2016/4/24.
 */
public class HomeworkModelImpl implements HomeworkModel {


    /**
     * 发布作业
     *
     * @param context
     * @param homework
     * @param resultCallback
     */
    @Override
    public void publishHomework(Context context, Teacher_Homework homework, SaveListener resultCallback) {
        Log.i(AddHomeworkFragment.TAG, "publishHomework c_id=" + homework.getC_id() + " title=" + homework.getTitle() +
                " content=" + homework.getContent());
        homework.save(context, resultCallback);
    }

    /**
     * 创建学生作业列表
     *
     * @param context
     */
    @Override
    public void createStudentHomeworkList(final Context context, final Teacher_Homework homework) {
        BmobQuery<Student_Course> query = new BmobQuery<>();
        query.addWhereEqualTo("c_id", homework.getC_id());
        query.findObjects(context, new FindListener<Student_Course>() {
            @Override
            public void onSuccess(List<Student_Course> list) {
                if (null != list && list.size() > 0) {
                    for (Student_Course course : list) {
                        Student_Homework student_homework = new Student_Homework();
                        student_homework.setStudent_number(course.getStudent_number());
                        student_homework.setStudent_name(course.getStudent_name());
                        student_homework.setAccount(course.getAccount());
                        student_homework.setC_id(course.getC_id());
                        student_homework.setH_id(homework.getH_id());
                        student_homework.setCommit_time(null);
                        student_homework.save(context);
                    }

                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }


    @Override
    public void getHomeworkList(Context context, int c_id, final ResultsCallback resultsCallback) {
        Log.i(AddHomeworkFragment.TAG, "getHomeworkList c_id=" + c_id);
        String sql = "select * from Teacher_Homework where c_id=?";
        BmobQuery<Teacher_Homework> bmobQuery = new BmobQuery<Teacher_Homework>();
        bmobQuery.doSQLQuery(context, sql, new SQLQueryListener<Teacher_Homework>() {
            @Override
            public void done(BmobQueryResult<Teacher_Homework> bmobQueryResult, BmobException e) {
                List list = bmobQueryResult.getResults();
                if (null != list) {
                    resultsCallback.onSuccess(list);
                } else {
                    resultsCallback.onSuccess(new ArrayList());
                    Log.i(AddHomeworkFragment.TAG, "getHomeworkList null");

                }
            }
        }, c_id);
    }

    @Override
    public void getStudentHomeworkList(Context context, int h_id, final ResultsCallback resultsCallback) {
        BmobQuery<Student_Homework> query = new BmobQuery<>();
        query.addWhereEqualTo("h_id", h_id);
        query.findObjects(context, new FindListener<Student_Homework>() {
            @Override
            public void onSuccess(List<Student_Homework> list) {
                if (null != list && list.size() > 0) {
                    resultsCallback.onSuccess(list);
                } else {
                    resultsCallback.onSuccess(new ArrayList());
                }
            }

            @Override
            public void onError(int i, String s) {
                Log.i(HomeworkFragment.TAG, "getStudentHomeworkList " + s);
            }
        });
    }


    /**
     * 批改作业
     *
     * @param context
     * @param homework
     * @param resultCallback
     */
    @Override
    public void correctHomework(final Context context, Student_Homework homework, UpdateListener resultCallback) {
        homework.update(context, resultCallback);

        BmobQuery<Teacher_Homework> query = new BmobQuery<>();
        query.addWhereEqualTo("h_id", homework.getH_id());
        query.findObjects(context, new FindListener<Teacher_Homework>() {
            @Override
            public void onSuccess(List<Teacher_Homework> list) {
                if (null != list && list.size() > 0) {
                    Teacher_Homework teacher_homework = list.get(0);
                    int check_count = teacher_homework.getCheck_count();
                    check_count++;
                    teacher_homework.setCheck_count(check_count);
                    int no_check_count = teacher_homework.getNo_check_count();
                    no_check_count--;
                    teacher_homework.setNo_check_count(no_check_count);
                    teacher_homework.update(context);

                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });


    }

    @Override
    public void deleteHomework(final Context context, int h_id) {
        BmobQuery<Teacher_Homework> query = new BmobQuery<>();
        query.addWhereEqualTo("h_id", h_id);
        query.findObjects(context, new FindListener<Teacher_Homework>() {
            @Override
            public void onSuccess(List<Teacher_Homework> list) {
                if (null != list && list.size() > 0) {
                    for (Teacher_Homework homework : list) {
                        homework.delete(context);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
        BmobQuery<Student_Homework> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("h_id", h_id);
        query1.findObjects(context, new FindListener<Student_Homework>() {
            @Override
            public void onSuccess(List<Student_Homework> list) {
                if (null != list && list.size() > 0) {
                    for (Student_Homework homework : list) {
                        homework.delete(context);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }


    @Override
    public void getSearchHomeworkList(Context context, String content, final ResultsCallback resultsCallback) {
        String sql = "select * from Teacher_Homework where title like '%" + content + "%' or content like '%" + content + "%'";
        BmobQuery<Teacher_Homework> query = new BmobQuery<>();
        query.doSQLQuery(context, sql, new SQLQueryListener<Teacher_Homework>() {
            @Override
            public void done(BmobQueryResult<Teacher_Homework> bmobQueryResult, BmobException e) {
                if (null != bmobQueryResult) {
                    List<Teacher_Homework> homeworks = bmobQueryResult.getResults();
                    List<Search> searches = new ArrayList<Search>();
                    if (null != homeworks && homeworks.size() > 0) {
                        for (Teacher_Homework homewokr : homeworks) {
                            Search search = new Search();
                            search.setType(1);
                            search.setType_id(homewokr.getH_id());
                            search.setTitle(homewokr.getTitle());
                            search.setContent(homewokr.getContent());
                            searches.add(search);
                        }
                        resultsCallback.onSuccess(searches);
                    } else {
                        resultsCallback.onSuccess(searches);
                    }
                } else {
                    resultsCallback.onFailure(e);
                }
            }
        });
    }


}
