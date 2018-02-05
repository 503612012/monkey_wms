package com.monkey.vo;

/**
 * 目录实体类
 * 
 * @author skyer
 */
public class Menu {

	private Integer id; // 目录表
	private String menuCode; // 目录编码
	private String menuName; // 目录名称
	private String roleId; // 角色Id
	private Integer pid; // 父Id
	private Integer sort; // 排序值
	private String url; // 目录链接
	private String iconCls; // 图标
	private String type; // 1目录,2按钮

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", menuCode=" + menuCode + ", menuName=" + menuName + ", roleId=" + roleId + ", pid=" + pid + ", sort=" + sort + ", url=" + url + ", iconCls=" + iconCls + ", type=" + type + "]";
	}

}
