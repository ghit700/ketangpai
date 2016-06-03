package com.ketangpai.modelImpl;

import android.content.Context;
import android.util.Log;

import com.ketangpai.bean.Search;
import com.ketangpai.bean.Student_Course;
import com.ketangpai.bean.Student_Homework;
import com.ketangpai.bean.Student_Reply;
import com.ketangpai.bean.Subject;
import com.ketangpai.bean.Teacher_Homework;
import com.ketangpai.bean.Test;
import com.ketangpai.callback.ResultCallback;
import com.ketangpai.callback.ResultsCallback;
import com.ketangpai.fragment.AddExamFragment;
import com.ketangpai.model.ExamModel;

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
 * Created by nan on 2016/5/5.
 */
public class ExamModelImpl implements ExamModel {

    @Override
    public void publishExam(Context context, Test test, SaveListener resultCallback) {
        test.save(context, resultCallback);
        List<Subject> subjects = test.getSubjects();
        for (Subject s : subjects) {
            s.save(context);
        }
    }

    @Override
    public void createStudentExamList(final Context context, final Test test) {
        BmobQuery<Student_Course> query = new BmobQuery<>();
        query.addWhereEqualTo("c_id", test.getC_id());
        query.findObjects(context, new FindListener<Student_Course>() {
            @Override
            public void onSuccess(List<Student_Course> list) {
                if (null != list && list.size() > 0) {
                    for (Student_Course course : list) {
                        Student_Reply student_reply = new Student_Reply();
                        student_reply.setStudent_number(course.getStudent_number());
                        student_reply.setStudent_name(course.getStudent_name());
                        student_reply.setAccount(course.getAccount());
                        student_reply.setC_id(course.getC_id());
                        student_reply.setT_id(test.getT_id());
                        student_reply.addAllUnique("subjects", test.getSubjects());
                        student_reply.setS_state("未交");
                        student_reply.setT_state("未交");
                        student_reply.save(context);
                    }

                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }

    @Override
    public void getExamList(Context context, int c_id, final ResultsCallback resultsCallback) {
        BmobQuery<Test> query = new BmobQuery<>();
        query.addWhereEqualTo("c_id", c_id);
        query.findObjects(context, new FindListener<Test>() {
            @Override
            public void onSuccess(List<Test> list) {
                if (null != list && list.size() > 0) {
                    resultsCallback.onSuccess(list);
                } else {
                    resultsCallback.onSuccess(new ArrayList());
                }
            }

            @Override
            public void onError(int i, String s) {
                Log.i(AddExamFragment.TAG, "getExamList " + s);
            }
        });

    }

    @Override
    public void getStudentExamList(Context context, int t_id, final ResultsCallback resultsCallback) {
        BmobQuery<Student_Reply> query = new BmobQuery<>();
        query.addWhereEqualTo("t_id", t_id);
        query.findObjects(context, new FindListener<Student_Reply>() {
            @Override
            public void onSuccess(List<Student_Reply> list) {
                if (null != list && list.size() > 0) {
                    resultsCallback.onSuccess(list);
                } else {
                    resultsCallback.onSuccess(new ArrayList());
                }
            }

            @Override
            public void onError(int i, String s) {
                Log.i("====", "getStudentExamList " + s);
            }
        });
    }


    @Override
    public void correctExam(final Context context, Student_Reply student_reply, UpdateListener resultCallback) {

        List<Subject> subjects = student_reply.getSubjects();
        for (Subject s : subjects) {
            int grade;
            if (null != student_reply.getGrade()) {
                grade = student_reply.getGrade() + s.getS_value();
                student_reply.setGrade(grade);
            } else {
                student_reply.setGrade(s.getS_value());
            }


        }
        student_reply.update(context, resultCallback);

        BmobQuery<Test> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("t_id", student_reply.getT_id());
        query1.findObjects(context, new FindListener<Test>() {
            @Override
            public void onSuccess(List<Test> list) {
                if (null != list && list.size() > 0) {
                    Test test = list.get(0);
                    int check_count = test.getCheck_count();
                    check_count++;
                    test.setCheck_count(check_count);
                    int no_check_count = test.getNo_check_count();
                    no_check_count--;
                    test.setNo_check_count(no_check_count);
                    test.update(context);

                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }


    @Override
    public void getSearchExamList(Context context, String content, final ResultsCallback resultsCallback) {
        String sql = "select * from Test where title like '%" + content + "%' or content like '%" + content + "%'";
        BmobQuery<Test> query = new BmobQuery<>();
        query.doSQLQuery(context, sql, new SQLQueryListener<Test>() {
            @Override
            public void done(BmobQueryResult<Test> bmobQueryResult, BmobException e) {
                if (null != bmobQueryResult) {
                    List<Test> tests = bmobQueryResult.getResults();
                    List<Search> searches = new ArrayList<Search>();
                    if (null != tests && tests.size() > 0) {
                        for (Test test : tests) {
                            Search search = new Search();
                            search.setType(3);
                            search.setType_id(test.getT_id());
                            search.setTitle(test.getTitle());
                            search.setContent(test.getContent());
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

    @Override
    public void deleteExam(final Context context, int t_id) {
        BmobQuery<Test> query = new BmobQuery<>();
        query.addWhereEqualTo("t_id", t_id);
        query.findObjects(context, new FindListener<Test>() {
            @Override
            public void onSuccess(List<Test> list) {
                if (null != list && list.size() > 0) {
                    for (Test test : list) {
                        test.delete(context);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
        BmobQuery<Student_Reply> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("t_id", t_id);
        query1.findObjects(context, new FindListener<Student_Reply>() {
            @Override
            public void onSuccess(List<Student_Reply> list) {
                if (null != list && list.size() > 0) {
                    for (Student_Reply student_reply : list) {
                        student_reply.delete(context);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    @Override
    public void loadChooseSubjects(Context context, int type, String text, final ResultsCallback resultsCallback) {
        String sql = "select * from Subject where type=" + type + " and title like '%" + text + "%'";
        BmobQuery<Subject> query = new BmobQuery<>();

        query.doSQLQuery(context, sql, new SQLQueryListener<Subject>() {
            @Override
            public void done(BmobQueryResult<Subject> bmobQueryResult, BmobException e) {
                List<Subject> subjects = bmobQueryResult.getResults();
                if (null != subjects && subjects.size() > 0) {
                    resultsCallback.onSuccess(subjects);
                } else {
                    resultsCallback.onSuccess(new ArrayList());
                }
            }
        });


    }
}
