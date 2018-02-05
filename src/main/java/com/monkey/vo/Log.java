package com.monkey.vo;

/**
 * 日志实体类
 * 
 * @author skyer
 */
public class Log {

	private Integer id; // 日志表
	private String title; // 操作标题
	private String content; // 操作内容
	private Integer userId; // 用户ID
	private String userName; // 用户名
	private String dateTime; // 操作时间
	private String ip; // 操作IP

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", title=" + title + ", content=" + content + ", userId=" + userId + ", userName=" + userName + ", dateTime=" + dateTime + ", ip=" + ip + "]";
	}

}
