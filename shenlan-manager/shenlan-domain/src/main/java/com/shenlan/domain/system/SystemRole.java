package com.shenlan.domain.system;

import java.util.Date;
import java.util.List;

import com.shenlan.domain.BaseEntity;

/**
 * 系统角色
 * @author Z
 *
 */
public class SystemRole extends BaseEntity {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 5338770473728548044L;

	private String name;

    private Integer roleLevel;

    private String roleNo;

    private Date createTime;

    private Integer isValid;

    private Date updateTime;
    
    //------------------------
    private List<SystemMenu> systemMenu;
    private String menuStr;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo == null ? null : roleNo.trim();
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

	public List<SystemMenu> getSystemMenu() {
		return systemMenu;
	}

	public void setSystemMenu(List<SystemMenu> systemMenu) {
		this.systemMenu = systemMenu;
	}

	public String getMenuStr() {
		return menuStr;
	}

	public void setMenuStr(String menuStr) {
		this.menuStr = menuStr;
	}
    
}