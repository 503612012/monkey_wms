package com.monkey.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.monkey.constant.Constant;
import com.monkey.controller.BaseController;
import com.monkey.enumerate.ResultEnum;
import com.monkey.service.UserService;
import com.monkey.vo.User;

/**
 * 用户控制器
 * 
 * @author skyer
 */
@Controller
@RequestMapping("/user/user")
public class UserController extends BaseController {

	private final static Logger L = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * 去到用户管理主页面
	 */
	@RequestMapping("/index")
	@RequiresPermissions("E1_01_04")
	public ModelAndView index() {
		try {
			ModelAndView mv = new ModelAndView("/view/user/user/index");
			return mv;
		} catch (Exception e) {
			L.error("--------------------------", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询用户列表
	 * 
	 * @param page 当前页码
	 * @param rows 每页显示数量
	 * @param keyword 搜索关键字
	 * @return
	 */
	@RequestMapping("/list")
	@RequiresPermissions("E1_01_04")
	@ResponseBody
	public Object list(Integer page, Integer rows, String keyword, HttpServletRequest req) {
		try {
			JSONArray list = userService.findByPage(page, rows, keyword);
			JSONObject result = new JSONObject();
			Long total = userService.total(keyword);
			result.put("rows", list);
			result.put("total", total);
			req.setAttribute("keyword", StringUtils.isEmpty(keyword) == true ? "" : keyword);
			return result;
		} catch (Exception e) {
			L.error("--------------------------", e);
			e.printStackTrace();
		}
		return super.fail(ResultEnum.SEARCH_ERROR.getCode(), ResultEnum.SEARCH_ERROR.getValue());
	}

	/**
	 * 添加或修改用户
	 */
	@RequestMapping("/addOrUpdate")
	@ResponseBody
	@RequiresPermissions("E1_01_04_02")
	public Object addOrUpdate(User user) {
		try {
			// 修改用户
			if (!StringUtils.isEmpty(user.getId())) {
				User userInDb = userService.findById(user.getId());
				Md5Hash md5 = new Md5Hash(user.getPassword(), Constant.MD5_SALT);
				userInDb.setPassword(md5.toString());
				userInDb.setRoleId(user.getRoleId());
				userService.update(userInDb);
				return super.success("修改成功！");
			}
			// 添加用户
			User u = userService.findByUserName(user.getUserName());
			if (u != null) {
				return super.fail(ResultEnum.USER_ALREADY_EXIST.getCode(), ResultEnum.USER_ALREADY_EXIST.getValue());
			}
			Md5Hash md5 = new Md5Hash(user.getPassword(), Constant.MD5_SALT);
			user.setPassword(md5.toString());
			user.setStatus(1);
			Integer result = userService.add(user);
			if (result > 0) {
				return super.success("添加成功！");
			}
		} catch (Exception e) {
			L.error("--------------------------", e);
			e.printStackTrace();
		}
		if (!StringUtils.isEmpty(user.getId())) {
			return super.fail(ResultEnum.UPDATE_ERROR.getCode(), ResultEnum.UPDATE_ERROR.getValue());
		} else {
			return super.fail(ResultEnum.INSERT_ERROR.getCode(), ResultEnum.INSERT_ERROR.getValue());
		}
	}

	/**
	 * 删除用户
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@RequiresPermissions("E1_01_04_01")
	public Object delete(Integer id) {
		try {
			Integer result = userService.delete(id);
			if (result > 0) {
				return super.success("删除成功！");
			}
		} catch (Exception e) {
			L.error("--------------------------", e);
			e.printStackTrace();
		}
		return super.fail(ResultEnum.DELETE_ERROR.getCode(), ResultEnum.DELETE_ERROR.getValue());
	}

	/**
	 * 更改用户状态
	 */
	@RequestMapping("/updateStatus")
	@ResponseBody
	@RequiresPermissions("E1_01_04_03")
	public Object updateStatus(Integer id, Integer status) {
		try {
			User user = userService.findById(id);
			user.setStatus(status);
			userService.update(user);
			return super.success("修改成功！");
		} catch (Exception e) {
			L.error("--------------------------", e);
			e.printStackTrace();
		}
		return super.fail(ResultEnum.DELETE_ERROR.getCode(), ResultEnum.DELETE_ERROR.getValue());
	}

}
