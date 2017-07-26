package com.shenlan.domain.vo;

import com.shenlan.domain.BaseEntity;

import java.util.Date;

/**
 * Created by YANG on 2016/12/23.
 */
public class ProjectPub extends BaseEntity {

    private String channelType;

    private String contentType;

    private String publishStatus;

    private Date publishTime;

    private Date offlineTime;

    private String airlineCode;

    private String airlineName;

    private String operator;

    private Date createTime;

    private Date updateTime;

    private Long articleCounts;

    private String projectId;

    private String projectName;

    private String bestProject;

    private String projectTop;

    private Date projectTopTime;

    private String hasArticle;


    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType == null ? null : channelType.trim();
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus == null ? null : publishStatus.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode == null ? null : airlineCode.trim();
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName == null ? null : airlineName.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
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



    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getBestProject() {
        return bestProject;
    }

    public void setBestProject(String bestProject) {
        this.bestProject = bestProject == null ? null : bestProject.trim();
    }

    public String getProjectTop() {
        return projectTop;
    }

    public void setProjectTop(String projectTop) {
        this.projectTop = projectTop == null ? null : projectTop.trim();
    }

    public Date getProjectTopTime() {
        return projectTopTime;
    }

    public void setProjectTopTime(Date projectTopTime) {
        this.projectTopTime = projectTopTime;
    }

    public String getHasArticle() {
        return hasArticle;
    }

    public void setHasArticle(String hasArticle) {
        this.hasArticle = hasArticle == null ? null : hasArticle.trim();
    }

    public Long getArticleCounts() {
        return articleCounts;
    }

    public void setArticleCounts(Long articleCounts) {
        this.articleCounts = articleCounts;
    }

}
