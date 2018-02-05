package com.monkey.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.monkey.mapper.MenuMapper;
import com.monkey.vo.Menu;
import com.monkey.vo.User;

/**
 * 菜单服务层
 * 
 * @author skyer
 */
@Service
public class MenuService {

	@Autowired
	private MenuMapper menuMapper;

	/**
	 * 获取目录树
	 * 
	 * @return
	 */
	public JSONArray getMenu(User user) {
		JSONArray result = new JSONArray(); // 最终返回结果
		List<Menu> levelOneMenus = menuMapper.getChildrenByPid(0, user.getRoleId(), 1); // 所有的一级目录
		for (Menu levelOneMenu : levelOneMenus) { // 遍历所有的一级目录
			JSONObject levelOneItem = new JSONObject(); // 一级目录的元素项
			levelOneItem.put("id", levelOneMenu.getMenuCode());
			levelOneItem.put("text", levelOneMenu.getMenuName());
			levelOneItem.put("iconCls", levelOneMenu.getIconCls());
			JSONArray levelOneChildrens = new JSONArray(); // 一级目录的子目录
			List<Menu> levelTwoMenus = menuMapper.getChildrenByPid(levelOneMenu.getId(), user.getRoleId(), 1); // 获取该一级目录的所有子目录
			if (levelTwoMenus.size() == 0) {
				continue;
			}
			levelOneItem.put("state", "closed");
			for (Menu levelTwoMenu : levelTwoMenus) { // 遍历所有的二级目录
				JSONObject levelTwoItem = new JSONObject(); // 二级目录的元素项
				levelTwoItem.put("id", levelTwoMenu.getMenuCode());
				levelTwoItem.put("text", levelTwoMenu.getMenuName());
				levelTwoItem.put("iconCls", levelTwoMenu.getIconCls());
				JSONObject levelTwoAttr = new JSONObject();
				levelTwoAttr.put("url", levelTwoMenu.getUrl());
				levelTwoItem.put("attributes", levelTwoAttr);
				levelOneChildrens.add(levelTwoItem);

				JSONArray levelTwoChildrens = new JSONArray(); // 二级目录的子目录
				List<Menu> levelThreeMenus = menuMapper.getChildrenByPid(levelTwoMenu.getId(), user.getRoleId(), 1); // 获取该二级目录的所有子目录
				if (levelThreeMenus.size() == 0) {
					continue;
				}
				levelTwoItem.put("state", "closed");
				for (Menu levelThreeMenu : levelThreeMenus) { // 遍历所有的三级目录
					JSONObject levelThreeItem = new JSONObject(); // 三级目录的元素项
					levelThreeItem.put("id", levelThreeMenu.getMenuCode());
					levelThreeItem.put("text", levelThreeMenu.getMenuName());
					levelThreeItem.put("iconCls", levelThreeMenu.getIconCls());
					JSONObject levelThreeAttr = new JSONObject();
					levelThreeAttr.put("url", levelThreeMenu.getUrl());
					levelThreeItem.put("attributes", levelThreeAttr);
					levelTwoChildrens.add(levelThreeItem);
				}
				levelTwoItem.put("children", levelTwoChildrens);
			}
			levelOneItem.put("children", levelOneChildrens);
			result.add(levelOneItem);
		}
		return result;
	}

	/**
	 * 获取一个目录的所有子目录
	 * 
	 * @param id 该目录的主键
	 * @param roleId 角色ID
	 * @param type 目录类型(1目录,2按钮)
	 */
	public List<Menu> getChildrenByPid(Integer id, Integer roleId, Integer type) {
		return menuMapper.getChildrenByPid(id, roleId, type);
	}

	/**
	 * 判断某个角色是否拥有某个目录权限
	 * 
	 * @param roleId 角色ID
	 * @param menuId 目录ID
	 * @return
	 */
	public Integer hasPermission(Integer roleId, Integer menuId) {
		return menuMapper.hasPermission(roleId, menuId);
	}

	/**
	 * 设置目录权限
	 * 
	 * @param roleId 角色ID
	 */
	public void setPermission(JSONObject obj, Integer roleId) {
		String menuCode = obj.getString("menuCode");
		Boolean checked = obj.getBoolean("checked");
		Menu menu = menuMapper.findByMenuCode(menuCode);
		if (checked) { // 授权
			// 没有这个权限的时候才授权，如果有，直接跳过
			String roleIds = menu.getRoleId();
			roleIds = "," + roleIds + ",";
			if (!roleIds.contains("," + roleId + ",")) {
				menu.setRoleId(menu.getRoleId() + "," + roleId);
			}
		} else { // 取消授权
			String roleIds = menu.getRoleId();
			roleIds = "," + roleIds + ",";
			String newRoleIds = roleIds.replace("," + roleId + ",", ",");
			if (",".equals(newRoleIds)) {
				newRoleIds = "";
			}
			menu.setRoleId(StringUtils.isEmpty(newRoleIds) == true ? "" : newRoleIds.substring(1, newRoleIds.length() - 1));
		}
		menuMapper.update(menu);
	}

	/**
	 * 查询一个角色的权限
	 * 
	 * @param roleId 角色ID
	 * @return
	 */
	public Set<String> findPermissionByRoleId(Integer roleId) {
		return menuMapper.findPermissionByRoleId(roleId);
	}

	/**
	 * 获取所有
	 */
	public JSONArray findAllAsJSONArray() {
		List<Menu> list = menuMapper.findAll();
		JSONArray result = new JSONArray();
		for (Menu menu : list) {
			JSONObject item = new JSONObject();
			item.put("id", menu.getId());
			item.put("name", menu.getMenuName());
			item.put("_parentId", menu.getPid() == 0 ? null : menu.getPid());
			item.put("iconCls", menu.getIconCls());
			item.put("role", menu.getRoleId());
			item.put("sort", menu.getSort());
			result.add(item);
		}
		return result;
	}

}
