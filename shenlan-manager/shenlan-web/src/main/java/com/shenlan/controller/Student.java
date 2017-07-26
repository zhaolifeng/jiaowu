package com.shenlan.controller;

/**
 * Created by 赵利锋 on 2017/3/19.
 */
//@Annotation_my
public class Student implements Person {
    @Override
    @Annotation_my(name="zhaoyicheng")
    public void name() {

    }

    @Override
    @Annotation_my(say = "我喜欢草莓")
    public void say() {

    }

    @Override
    @Annotation_my(age=5)
    public void age() {

    }
}
