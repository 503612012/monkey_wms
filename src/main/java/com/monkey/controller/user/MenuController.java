package com.monkey.controller.user;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.monkey.controller.BaseController;
import com.monkey.enumerate.ResultEnum;
import com.monkey.service.MenuService;
import com.monkey.service.RoleService;
import com.monkey.vo.Role;

/**
 * 目录控制器
 * 
 * @author skyer
 */
@Controller
@RequestMapping("/user/menu")
public class MenuController extends BaseController {

	private final static Logger L = Logger.getLogger(MenuController.class);

	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleService roleService;

	/**
	 * 去到目录管理主页面
	 */
	@RequestMapping("/index")
	@RequiresPermissions("E1_01_05")
	public ModelAndView index() {
		try {
			ModelAndView mv = new ModelAndView("/view/user/menu/index");
			return mv;
		} catch (Exception e) {
			L.error("--------------------------", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询目录列表
	 * 
	 * @param page 当前页码
	 * @param rows 每页显示数量
	 * @return
	 */
	@RequestMapping("/list")
	@RequiresPermissions("E1_01_05")
	@ResponseBody
	public Object list(Integer page, Integer rows) {
		try {
			JSONArray list = menuService.findAllAsJSONArray();
			JSONObject result = new JSONObject();
			result.put("rows", list);
			result.put("total", 0);
			return result;
		} catch (Exception e) {
			L.error("--------------------------", e);
			e.printStackTrace();
		}
		return super.fail(ResultEnum.SEARCH_ERROR.getCode(), ResultEnum.SEARCH_ERROR.getValue());
	}

	/**
	 * 分配权限页面
	 */
	@RequestMapping("/setPermissionPage")
	@ResponseBody
	@RequiresPermissions("E1_01_04_04")
	public ModelAndView setPermissionPage() {
		try {
			ModelAndView mv = new ModelAndView("view/user/user/setPermission");
			List<Role> list = roleService.findAll();
			mv.addObject("list", list);
			return mv;
		} catch (Exception e) {
			L.error("--------------------------", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 设置权限
	 * 
	 * @param data 目录编码的集合以及是否选中
	 * @param roleId 角色ID
	 * @return
	 */
	@RequestMapping("/setPermission")
	@ResponseBody
	public Object setPermission(String data, Integer roleId) {
		try {
			JSONArray arr = JSONArray.parseArray(data);
			for (int i = 0; i < arr.size(); i++) {
				menuService.setPermission(arr.getJSONObject(i), roleId);
			}
			return super.success("设置成功！");
		} catch (Exception e) {
			L.error("--------------------------", e);
			e.printStackTrace();
		}
		return super.fail(ResultEnum.UPDATE_ERROR.getCode(), ResultEnum.UPDATE_ERROR.getValue());
	}

}
