package com.monkey.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.monkey.vo.Menu;

/**
 * MenuMapper接口
 * 
 * @author skyer
 */
public interface MenuMapper {

	/**
	 * 添加
	 */
	void add(Menu menu);

	/**
	 * 修改
	 */
	void update(Menu menu);

	/**
	 * 获取一个目录的所有子目录
	 * 
	 * @param id 该目录的主键
	 * @param roleId 角色ID
	 * @param type 目录类型(1目录,2按钮)
	 */
	List<Menu> getChildrenByPid(@Param("id") Integer id, @Param("roleId") Integer roleId, @Param("type") Integer type);

	/**
	 * 判断某个角色是否拥有某个目录权限
	 * 
	 * @param roleId 角色ID
	 * @param menuId 目录ID
	 * @return
	 */
	Integer hasPermission(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

	/**
	 * 通过目录编码查询
	 * 
	 * @param menuCode 目录编码
	 * @return
	 */
	Menu findByMenuCode(String menuCode);

	/**
	 * 查询一个角色的权限
	 * 
	 * @param roleId 角色ID
	 * @return
	 */
	Set<String> findPermissionByRoleId(Integer roleId);

	/**
	 * 查询所有
	 */
	List<Menu> findAll();

}
