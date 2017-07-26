package com.shenlan.domain.system;

import java.util.Date;
import java.util.List;

import com.shenlan.domain.BaseEntity;

/**
 * 系统用户
 * 
 * @author Z
 * 
 */
public class SystemUser extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3839327850531327800L;

	private String username;

	private String password;

	private String nickname;

	private String userNo;

	private Date createTime;

	private Integer isValid;

	private Date updateTime;

	private Date lastLoginTime;

	private String lastLoginIp;
	
	//------------------------
	private Long srId;//选中的角色id，目前是只能单选
	private String roleStr;
	
	private List<SystemRole> systemRole;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo == null ? null : userNo.trim();
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

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
	}

	public List<SystemRole> getSystemRole() {
		return systemRole;
	}

	public void setSystemRole(List<SystemRole> systemRole) {
		this.systemRole = systemRole;
	}

	public Long getSrId() {
		return srId;
	}

	public void setSrId(Long srId) {
		this.srId = srId;
	}

	public String getRoleStr() {
		return roleStr;
	}

	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
	}

	
	
}