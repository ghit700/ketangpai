package com.ketangpai.fragment;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.ketangpai.adapter.AddExamAdapter;
import com.ketangpai.adapter.ChooseSubjectAdapte;
import com.ketangpai.base.BasePresenterFragment;
import com.ketangpai.bean.Subject;
import com.ketangpai.bean.Teacher_Course;
import com.ketangpai.bean.Test;
import com.ketangpai.event.AddExamEvent;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.presenter.AddExamPresenter;
import com.ketangpai.viewInterface.AddExamViewInterface;
import com.shamanland.fab.ShowHideOnScroll;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nan on 2016/5/3.
 */
public class AddExamFragment extends BasePresenterFragment<AddExamViewInterface, AddExamPresenter> implements AddExamViewInterface, View.OnClickListener {
    public static final String TAG = "===AddExamFragment";
    //view
    private FloatingActionButton btnExamJudge;
    private FloatingActionButton btnExamSingleSelection;
    private FloatingActionButton btnExamMultipleSelection;
    private FloatingActionButton btnExamShortAnswer;
    private FloatingActionsMenu btnExamAdd;
    private RecyclerView listExam;

    //adapter
    private AddExamAdapter mAddExamAdapter;
    private ChooseSubjectAdapte mChooseSubjectAdapte;

    //various
    private List<Subject> mExams;
    private List<Subject> mAllSubjects;
    private List<Subject> mSaveSubjects;
    private List<Subject> mChooseSubjects;
    private String mTitle;
    private String mContent;
    private long mTime;
    private Teacher_Course mCourse;
    public static final int RESULT = 15;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_addexam;
    }

    @Override
    protected void initVarious() {
        super.initVarious();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mContent = getActivity().getIntent().getStringExtra("content");
        mTitle = getActivity().getIntent().getStringExtra("title");
        mCourse = (Teacher_Course) getActivity().getIntent().getSerializableExtra("course");
        mTime = getActivity().getIntent().getLongExtra("time", -1);
        mSaveSubjects = new ArrayList<>();
        mAllSubjects = new ArrayList<>();
        mChooseSubjects = new ArrayList<>();
        mChooseSubjectAdapte = new ChooseSubjectAdapte(mContext, mAllSubjects);
    }

    @Override
    protected void initView() {
        btnExamJudge = (FloatingActionButton) view.findViewById(R.id.btn_exam_judge);
        btnExamSingleSelection = (FloatingActionButton) view.findViewById(R.id.btn_exam_single_selection);
        btnExamMultipleSelection = (FloatingActionButton) view.findViewById(R.id.btn_exam_multiple_selection);
        btnExamShortAnswer = (FloatingActionButton) view.findViewById(R.id.btn_exam_short_answer);
        btnExamAdd = (FloatingActionsMenu) view.findViewById(R.id.btn_exam_add);
        listExam = (RecyclerView) view.findViewById(R.id.list_exam);

        listExam.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mExams = new ArrayList();
        mAddExamAdapter = new AddExamAdapter(mContext, mExams);
        listExam.setAdapter(mAddExamAdapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        btnExamJudge.setOnClickListener(this);
        btnExamSingleSelection.setOnClickListener(this);
        btnExamMultipleSelection.setOnClickListener(this);
        btnExamShortAnswer.setOnClickListener(this);
        listExam.setOnTouchListener(new ShowHideOnScroll(btnExamAdd));

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exam_judge:
                showSubjectChooseDialog(1);
                break;
            case R.id.btn_exam_single_selection:
                showSubjectChooseDialog(2);

                break;
            case R.id.btn_exam_multiple_selection:
                showSubjectChooseDialog(3);

                break;
            case R.id.btn_exam_short_answer:
                showSubjectChooseDialog(4);

                break;

            default:
                break;
        }
    }

    private void showSubjectChooseDialog(final int type) {
        btnExamAdd.collapse();
        mChooseSubjectAdapte.clearData();
        final AlertDialog dialog = new AlertDialog.Builder(mContext).create();


        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_choose_exam_subject, null);
        RecyclerView listChooseExamSubjects = (RecyclerView) view.findViewById(R.id.list_choose_exam_subject);
        CheckBox cbChooseExamAll = (CheckBox) view.findViewById(R.id.cb_choose_exam_allCheck);
        Button btnChooseExamCreate = (Button) view.findViewById(R.id.btn_choose_exam_create);
        Button btnChooseExamAdd = (Button) view.findViewById(R.id.btn_choose_exam_add);

        listChooseExamSubjects.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        listChooseExamSubjects.setAdapter(mChooseSubjectAdapte);

        cbChooseExamAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (Subject s : mAllSubjects) {
                    s.setCheck(isChecked);
                }
                mChooseSubjectAdapte.notifyDataSetChanged();
            }
        });


        btnChooseExamAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Subject subject : mAllSubjects) {
                    if (subject.isCheck()) {
                        mChooseSubjects.add(subject);
                        mAddExamAdapter.addItem(mExams.size(), subject);
                    }
                }
                dialog.dismiss();
            }
        });


        btnChooseExamCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (type) {
                    case 1:
                        addJudgeSubject();
                        break;
                    case 2:
                        addSingleSelectionSubject();
                        break;
                    case 3:
                        addMultipleSelectionSubject();
                        break;
                    case 4:
                        addShortAnswerSubject();
                        break;
                }
                dialog.dismiss();
            }
        });

        dialog.setView(view);
        dialog.show();
        mPresenter.loadChooseSubjects(mContext, type);
    }

    private void addShortAnswerSubject() {
        Subject subject = new Subject();
        subject.setType(4);
        mSaveSubjects.add(subject);
        mAddExamAdapter.addItem(mExams.size(), subject);
        listExam.post(new Runnable() {
            @Override
            public void run() {
                listExam.smoothScrollToPosition(mExams.size());
            }
        });
    }

    private void addSingleSelectionSubject() {
        Subject subject = new Subject();
        subject.setType(2);
        mSaveSubjects.add(subject);
        mAddExamAdapter.addItem(mExams.size(), subject);
        listExam.post(new Runnable() {
            @Override
            public void run() {
                listExam.smoothScrollToPosition(mExams.size());
            }
        });
    }

    private void addMultipleSelectionSubject() {
        Subject subject = new Subject();
        subject.setType(3);
        mSaveSubjects.add(subject);

        mAddExamAdapter.addItem(mExams.size(), subject);
        listExam.post(new Runnable() {
            @Override
            public void run() {
                listExam.smoothScrollToPosition(mExams.size());
            }
        });
    }

    private void addJudgeSubject() {
        Subject subject = new Subject();
        subject.setType(1);
        mSaveSubjects.add(subject);
        mAddExamAdapter.addItem(mExams.size(), subject);
        listExam.post(new Runnable() {
            @Override
            public void run() {
                listExam.smoothScrollToPosition(mExams.size());
            }
        });
    }

    public void publishExam() {
        if (judgeExamComplete()) {
            Test test = new Test();
            test.setTitle(mTitle);
            test.setContent(mContent);
            test.setC_id(mCourse.getC_id());
            test.setCheck_count(0);
            test.setNo_check_count(0);
            test.setNo_hander_count(mCourse.getNumbers());
            test.setE_time(mTime);
            test.addAllUnique("subjects", mExams);
            test.setSubjects(mSaveSubjects);
            test.setP_time(System.currentTimeMillis());
            mPresenter.publishExam(mContext, test, mCourse.getC_id(), mCourse.getName());
        } else {
            sendToast("请确认所有题目");
        }

    }

    private boolean judgeExamComplete() {

        for (Subject s : mExams) {
            if (!s.isSubmit()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addExamOnComplete(Test test) {

        EventBus.getDefault().post(new AddExamEvent(test));
        sendToast("测试发布成功");
        getActivity().finish();
    }

    @Override
    public void loadChooseSubjectsOnComplete(List<Subject> subjects) {
        for (Subject subject : subjects) {
            for (Subject chooseSubject : mChooseSubjects) {
                if (subject.getS_id() == chooseSubject.getS_id()) {
                    subjects.remove(subject);
                    break;
                }
            }
        }
        mAllSubjects.clear();
        mAllSubjects.addAll(subjects);
        mChooseSubjectAdapte.notifyDataSetChanged();

    }

    @Override
    protected AddExamPresenter createPresenter() {
        return new AddExamPresenter();
    }
}
