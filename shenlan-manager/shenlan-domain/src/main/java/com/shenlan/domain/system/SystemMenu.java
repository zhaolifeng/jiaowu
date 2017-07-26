package com.shenlan.domain.system;

import java.util.Date;

import com.shenlan.domain.BaseEntity;

/**
 * 系统菜单
 * @author Z
 *
 */
public class SystemMenu extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2761334483370601927L;

	private String name;

	private String alias;

	private Integer sort;

	private Integer parentId;

	private String parentIds;

	private String url;

	private String icon;//未选中的图片

	private Integer isShow;

	private String permission;

	private Date createTime;

	private Integer isValid;

	private Date updateTime;

	private String sicon;//选中的图片
	
	//------------------------
	private String uicon;//当前使用的图片，需要根据页面选中菜单进行灵活设置

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias == null ? null : alias.trim();
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds == null ? null : parentIds.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon == null ? null : icon.trim();
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission == null ? null : permission.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}


	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getSicon() {
		return sicon;
	}

	public void setSicon(String sicon) {
		this.sicon = sicon;
	}

	public String getUicon() {
		return uicon;
	}

	public void setUicon(String uicon) {
		this.uicon = uicon;
	}
	
	
}