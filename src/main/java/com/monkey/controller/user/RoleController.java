package com.monkey.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.monkey.controller.BaseController;
import com.monkey.enumerate.ResultEnum;
import com.monkey.service.RoleService;
import com.monkey.vo.Role;

/**
 * 角色控制器
 * 
 * @author skyer
 */
@Controller
@RequestMapping("/user/role")
public class RoleController extends BaseController {

	private final static Logger L = Logger.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;

	/**
	 * 去到角色管理主页面
	 */
	@RequestMapping("/index")
	@RequiresPermissions("E1_01_02")
	public ModelAndView index() {
		try {
			ModelAndView mv = new ModelAndView("/view/user/role/index");
			return mv;
		} catch (Exception e) {
			L.error("--------------------------", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询角色列表
	 * 
	 * @param page 当前页码
	 * @param rows 每页显示数量
	 * @param keyword 搜索关键字
	 * @return
	 */
	@RequestMapping("/list")
	@RequiresPermissions("E1_01_02")
	@ResponseBody
	public Object list(Integer page, Integer rows, String keyword, HttpServletRequest req) {
		try {
			JSONArray list = roleService.findByPage(page, rows, keyword);
			JSONObject result = new JSONObject();
			Long total = roleService.total(keyword);
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
	 * 查询所有角色
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public Object findAll() {
		try {
			List<Role> list = roleService.findAll();
			JSONArray arr = new JSONArray();
			JSONObject tmp = new JSONObject();
			tmp.put("id", "");
			tmp.put("roleName", "--请选择--");
			arr.add(tmp);
			for (Role role : list) {
				JSONObject obj = new JSONObject();
				obj.put("id", role.getId());
				obj.put("roleName", role.getRoleName());
				arr.add(obj);
			}
			return arr;
		} catch (Exception e) {
			L.error("--------------------------", e);
			e.printStackTrace();
		}
		return super.fail(ResultEnum.SEARCH_ERROR.getCode(), ResultEnum.SEARCH_ERROR.getValue());
	}

	/**
	 * 获取某个角色的权限树
	 * 
	 * @param roleId 角色ID
	 * @return
	 */
	@RequestMapping("/getPermissonTreeData")
	@ResponseBody
	public Object getPermissonTreeData(Integer roleId) {
		try {
			JSONArray tree = roleService.getPermissonTreeData(roleId);
			return tree;
		} catch (Exception e) {
			L.error("--------------------------", e);
			e.printStackTrace();
		}
		return super.fail(ResultEnum.SEARCH_ERROR.getCode(), ResultEnum.SEARCH_ERROR.getValue());
	}

	/**
	 * 添加或修改角色
	 */
	@RequestMapping("/addOrUpdate")
	@ResponseBody
	@RequiresPermissions("E1_01_02_02")
	public Object addOrUpdate(Role role) {
		try {
			// 修改角色
			if (!StringUtils.isEmpty(role.getId())) {
				Role r = roleService.findByRoleName(role.getRoleName());
				if (r != null) {
					return super.fail(ResultEnum.ROLE_ALREADY_EXIST.getCode(), ResultEnum.ROLE_ALREADY_EXIST.getValue());
				}
				Role roleInDb = roleService.findById(role.getId());
				roleInDb.setRoleName(role.getRoleName());
				roleService.update(role);
				return super.success("修改成功！");
			}
			// 添加角色
			Role r = roleService.findByRoleName(role.getRoleName());
			if (r != null) {
				return super.fail(ResultEnum.ROLE_ALREADY_EXIST.getCode(), ResultEnum.ROLE_ALREADY_EXIST.getValue());
			}
			Integer result = roleService.add(role);
			if (result > 0) {
				return super.success("添加成功！");
			}
		} catch (Exception e) {
			L.error("--------------------------", e);
			e.printStackTrace();
		}
		if (!StringUtils.isEmpty(role.getId())) {
			return super.fail(ResultEnum.UPDATE_ERROR.getCode(), ResultEnum.UPDATE_ERROR.getValue());
		} else {
			return super.fail(ResultEnum.INSERT_ERROR.getCode(), ResultEnum.INSERT_ERROR.getValue());
		}
	}

	/**
	 * 删除角色
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@RequiresPermissions("E1_01_02_01")
	public Object delete(Integer id) {
		try {
			Integer result = roleService.delete(id);
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
	 * 通过ID列表获取
	 */
	@RequestMapping("/findByIds")
	@ResponseBody
	public Object findByIds(String ids) {
		try {
			List<Role> list = roleService.findByIds(ids.split(","));
			return super.success(list);
		} catch (Exception e) {
			L.error("--------------------------", e);
			e.printStackTrace();
		}
		return super.fail(ResultEnum.SEARCH_ERROR.getCode(), ResultEnum.SEARCH_ERROR.getValue());
	}

}
