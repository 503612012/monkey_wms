package com.monkey.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.monkey.mapper.RoleMapper;
import com.monkey.util.PageUtil;
import com.monkey.vo.Menu;
import com.monkey.vo.Role;

/**
 * 角色服务层
 * 
 * @author skyer
 */
@Service
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private MenuService menuService;

	/**
	 * 查询所有角色
	 */
	public List<Role> findAll() {
		return roleMapper.findAll();
	}

	/**
	 * 获取某个角色的权限树
	 * 
	 * @param roleId 角色ID
	 * @return
	 */
	public JSONArray getPermissonTreeData(Integer roleId) {
		JSONArray result = new JSONArray(); // 最终返回结果
		List<Menu> levelOneMenus = menuService.getChildrenByPid(0, null, null); // 所有的一级目录
		for (Menu levelOneMenu : levelOneMenus) { // 遍历所有的一级目录
			JSONObject levelOneItem = new JSONObject(); // 一级目录的元素项
			levelOneItem.put("id", levelOneMenu.getMenuCode());
			levelOneItem.put("text", levelOneMenu.getMenuName());
			levelOneItem.put("checked", hasPermission(roleId, levelOneMenu.getId()));
			levelOneItem.put("iconCls", levelOneMenu.getIconCls());
			JSONArray levelOneChildrens = new JSONArray(); // 一级目录的子目录
			List<Menu> levelTwoMenus = menuService.getChildrenByPid(levelOneMenu.getId(), null, null); // 获取该一级目录的所有子目录
			if (levelTwoMenus.size() == 0) {
				continue;
			}
			levelOneItem.put("state", "closed");
			for (Menu levelTwoMenu : levelTwoMenus) { // 遍历所有的二级目录
				JSONObject levelTwoItem = new JSONObject(); // 二级目录的元素项
				levelTwoItem.put("id", levelTwoMenu.getMenuCode());
				levelTwoItem.put("text", levelTwoMenu.getMenuName());
				levelTwoItem.put("checked", hasPermission(roleId, levelTwoMenu.getId()));
				levelTwoItem.put("iconCls", levelTwoMenu.getIconCls());
				levelOneChildrens.add(levelTwoItem);

				JSONArray levelTwoChildrens = new JSONArray(); // 二级目录的子目录
				List<Menu> levelThreeMenus = menuService.getChildrenByPid(levelTwoMenu.getId(), null, null); // 获取该二级目录的所有子目录
				if (levelThreeMenus.size() == 0) {
					continue;
				}
				levelTwoItem.put("state", "closed");
				for (Menu levelThreeMenu : levelThreeMenus) { // 遍历所有的三级目录
					JSONObject levelThreeItem = new JSONObject(); // 三级目录的元素项
					levelThreeItem.put("id", levelThreeMenu.getMenuCode());
					levelThreeItem.put("text", levelThreeMenu.getMenuName());
					levelThreeItem.put("checked", hasPermission(roleId, levelThreeMenu.getId()));
					levelThreeItem.put("iconCls", levelThreeMenu.getIconCls());
					levelTwoChildrens.add(levelThreeItem);

					JSONArray levelThreeChildrens = new JSONArray(); // 三级目录的子目录
					List<Menu> levelFourMenus = menuService.getChildrenByPid(levelThreeMenu.getId(), null, null); // 获取该三级目录的所有子目录
					if (levelFourMenus.size() == 0) {
						continue;
					}
					levelThreeItem.put("state", "closed");
					for (Menu levelFourMenu : levelFourMenus) { // 遍历所有的四级目录
						JSONObject levelFourItem = new JSONObject(); // 四级目录的元素项
						levelFourItem.put("id", levelFourMenu.getMenuCode());
						levelFourItem.put("text", levelFourMenu.getMenuName());
						levelFourItem.put("checked", hasPermission(roleId, levelFourMenu.getId()));
						levelFourItem.put("iconCls", levelFourMenu.getIconCls());
						levelThreeChildrens.add(levelFourItem);
					}
					levelThreeItem.put("children", levelThreeChildrens);
				}
				levelTwoItem.put("children", levelTwoChildrens);
			}
			levelOneItem.put("children", levelOneChildrens);
			result.add(levelOneItem);
		}
		return result;
	}

	/**
	 * 判断某个角色是否拥有某个目录权限
	 * 
	 * @param roleId 角色ID
	 * @param menuId 目录ID
	 * @return
	 */
	private boolean hasPermission(Integer roleId, Integer menuId) {
		Integer num = menuService.hasPermission(roleId, menuId);
		return num > 0;
	}

	/**
	 * 分页查询角色
	 * 
	 * @param pu 分页实体类
	 */
	public JSONArray findByPage(Integer pageNum, Integer pageSize, String keyword) {
		PageUtil pu = new PageUtil(pageNum, pageSize);
		List<Map<String, Object>> list = roleMapper.findByPage(pu, keyword);
		JSONArray result = new JSONArray();
		for (Map<String, Object> map : list) {
			result.add(map);
		}
		return result;
	}

	/**
	 * 统计角色总数量
	 */
	public Long total(String keyword) {
		return roleMapper.total(keyword);
	}

	/**
	 * 通过主键获取
	 */
	public Role findById(Integer id) {
		return roleMapper.findById(id);
	}

	/**
	 * 更新
	 */
	public void update(Role role) {
		roleMapper.update(role);
	}

	/**
	 * 通过角色名称获取
	 */
	public Role findByRoleName(String roleName) {
		return roleMapper.findByRoleName(roleName);
	}

	/**
	 * 添加
	 */
	public Integer add(Role role) {
		return roleMapper.add(role);
	}

	/**
	 * 删除角色
	 */
	public Integer delete(Integer id) {
		return roleMapper.delete(id);
	}

	/**
	 * 通过ID列表获取
	 */
	public List<Role> findByIds(String[] ids) {
		return roleMapper.findByIds(ids);
	}

}
