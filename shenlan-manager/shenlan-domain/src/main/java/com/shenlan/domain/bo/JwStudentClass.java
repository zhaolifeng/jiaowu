package com.shenlan.domain.bo;

import com.shenlan.domain.BaseEntity;

import java.util.Date;

public class JwStudentClass extends BaseEntity {

    private Integer classesId;
    private Integer studentId;
    private Integer payStatus;
    private double cost;
    private Date createTime;
    private Date updateTime;
    private JwStudent student;
    private JwClasses classes;
    private JwCourse course;
    private String operator;
    private int attendanceCount;


    public int getAttendanceCount() {
        return attendanceCount;
    }

    public void setAttendanceCount(int attendanceCount) {
        this.attendanceCount = attendanceCount;
    }


    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public JwCourse getCourse() {
        return course;
    }

    public void setCourse(JwCourse course) {
        this.course = course;
    }

    public JwStudent getStudent() {
        return student;
    }

    public void setStudent(JwStudent student) {
        this.student = student;
    }

    public JwClasses getClasses() {
        return classes;
    }

    public void setClasses(JwClasses classes) {
        this.classes = classes;
    }

    public Integer getClassesId() {
        return classesId;
    }

    public void setClassesId(Integer classesId) {
        this.classesId = classesId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}