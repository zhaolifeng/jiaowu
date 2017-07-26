package com.shenlan.domain.system;

import java.util.Date;

import com.shenlan.domain.BaseEntity;

/**
 * 系统角色菜单
 * @author Z
 *
 */
public class SystemRoleMenu extends BaseEntity {
    

    /**
	 * 
	 */
	private static final long serialVersionUID = 2586975013151798913L;

	private Long roleId;

    private Long menuId;

    private Date createTime;

    private Date updateTime;

   
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
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