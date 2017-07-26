package com.shenlan.domain.system;

import java.io.Serializable;
/**
 * 树
 * @author Z
 *
 */
public class TreeNode implements Serializable {

	private static final long serialVersionUID = 2331513209270656666L;

	private Long id;
	private String name;
	private Long pId;
	private String icon;
	private String value;
	private boolean checked;
	private boolean open;
	private Long parentId;
	
	public TreeNode() {
		super();
	}
	public TreeNode(Long id, String name, Long pId, String value) {
		super();
		this.id = id;
		this.name = name;
		this.pId = pId;
		this.setValue(value);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	
}
