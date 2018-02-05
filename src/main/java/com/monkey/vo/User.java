package com.monkey.vo;

/**
 * 用户实体类
 * 
 * @author skyer
 */
public class User {

	private Integer id; // 主键
	private String userName; // 用户名
	private String password; // 密码
	private Integer status; // 状态(1正常,2禁用)
	private Integer roleId; //用户角色
	

	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", status=" + status
				+ ", roleId=" + roleId + "]";
	}


}
