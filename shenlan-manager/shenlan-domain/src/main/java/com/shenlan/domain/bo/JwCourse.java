package com.shenlan.domain.bo;

import com.shenlan.domain.BaseEntity;

import java.util.Date;

public class JwCourse extends BaseEntity {
    private  String courseName;
    private  String courseContent;
    private  Integer courseMethods;
    private  double coursePeriod;
    private int courseStatus;
    private Date updateTime;
    private Date createTime;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }

    public Integer getCourseMethods() {
        return courseMethods;
    }

    public void setCourseMethods(Integer courseMethods) {
        this.courseMethods = courseMethods;
    }

    public double getCoursePeriod() {
        return coursePeriod;
    }

    public void setCoursePeriod(double coursePeriod) {
        this.coursePeriod = coursePeriod;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(int courseStatus) {
        this.courseStatus = courseStatus;
    }
}