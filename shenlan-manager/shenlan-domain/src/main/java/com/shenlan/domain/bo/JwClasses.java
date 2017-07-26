package com.shenlan.domain.bo;

import com.shenlan.domain.BaseEntity;

public class JwClasses extends BaseEntity {

    private String classesName;
    private Integer courseId;
    private Integer campusId;
    private double fee;
    private String courseName;
    private double coursePeriod;


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCoursePeriod() {
        return coursePeriod;
    }

    public void setCoursePeriod(double coursePeriod) {
        this.coursePeriod = coursePeriod;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getCampusId() {
        return campusId;
    }

    public void setCampusId(Integer campusId) {
        this.campusId = campusId;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }


}