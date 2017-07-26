package com.shenlan.domain.vo;

import com.shenlan.domain.BaseEntity;

public class CampaignPrizeRecord extends BaseEntity {

	private Integer campaignId;

    private Integer prizeCounts;

	public Integer getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}
	
	public Integer getPrizeCounts() {
		return prizeCounts;
	}

	public void setPrizeCounts(Integer prizeCounts) {
		this.prizeCounts = prizeCounts;
	}

}