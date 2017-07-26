package com.shenlan.domain.vo;

import com.shenlan.domain.BaseEntity;

/**
 * 活动用户积分排名
 * @author Z
 *
 */
public class CampaignIntegeralUser extends BaseEntity {

	private static final long serialVersionUID = -8063592656297848066L;
	private long campaignId;
	private long userId;
	private String userName;
	private String gameName;
	
	public long getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(long campaignId) {
		this.campaignId = campaignId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
}
