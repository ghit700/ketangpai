package com.ketangpai.event;

import com.ketangpai.bean.Test;

/**
 * Created by nan on 2016/5/8.
 */
public class AddExamEvent {
    Test test;

    public AddExamEvent(Test test) {
        this.test = test;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
