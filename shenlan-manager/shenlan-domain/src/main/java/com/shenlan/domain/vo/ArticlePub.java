package com.shenlan.domain.vo;

import com.shenlan.domain.BaseEntity;

import java.util.Date;

/**
 * Created by YANG on 2016/12/23.
 */
public class ArticlePub extends BaseEntity {

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


    private String articleId;

    private String articleName;

    private String articleType;

    private String bestArticle;

    private String articleTop;

    private Date articleTopTime;

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



    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName == null ? null : articleName.trim();
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType == null ? null : articleType.trim();
    }

    public String getBestArticle() {
        return bestArticle;
    }

    public void setBestArticle(String bestArticle) {
        this.bestArticle = bestArticle == null ? null : bestArticle.trim();
    }

    public String getArticleTop() {
        return articleTop;
    }

    public void setArticleTop(String articleTop) {
        this.articleTop = articleTop == null ? null : articleTop.trim();
    }

    public Date getArticleTopTime() {
        return articleTopTime;
    }

    public void setArticleTopTime(Date articleTopTime) {
        this.articleTopTime = articleTopTime;
    }

}
