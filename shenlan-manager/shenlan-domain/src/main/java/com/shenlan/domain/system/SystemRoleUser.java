package com.shenlan.domain.system;

import java.util.Date;

import com.shenlan.domain.BaseEntity;

/**
 * 系统角色用户
 * @author Z
 *
 */
public class SystemRoleUser extends BaseEntity {
    

    /**
	 * 
	 */
	private static final long serialVersionUID = -5704864091257164754L;

	private Long userId;

    private Long roleId;

    private Date createTime;

    private Date updateTime;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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