package com.monkey.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.monkey.constant.Constant;
import com.monkey.enumerate.ResultEnum;
import com.monkey.util.IPUtils;
import com.monkey.util.ResultInfo;
import com.monkey.vo.User;

/**
 * 基类Controller
 * 
 * @author skyer
 */
public abstract class BaseController {

	/**
	 * 请求成功
	 * 
	 * @param data 请求成功返回的内容
	 * @return
	 */
	public Object success(Object data) {
		ResultInfo<Object> resultInfo = new ResultInfo<Object>();
		resultInfo.setCode(ResultEnum.SUCCESS.getCode());
		resultInfo.setData(data);
		return resultInfo;
	}

	/**
	 * 请求失败
	 * 
	 * @param msg 失败信息
	 * @return
	 */
	public Object fail(Integer code, String msg) {
		ResultInfo<Object> resultInfo = new ResultInfo<Object>();
		resultInfo.setCode(code);
		resultInfo.setData(msg);
		return resultInfo;
	}

	/**
	 * 获取当天登录用户
	 * 
	 * @return 当前登录的用户
	 */
	public User getCurrentUser() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		User user = (User) req.getSession().getAttribute(Constant.CURRENT_USER);
		return user;
	}

	/**
	 * 获取当天登录用户的IP地址
	 */
	public String getCurrentUserIp(HttpServletRequest req) {
		return IPUtils.getClientIPAddr(req);
	}

}
