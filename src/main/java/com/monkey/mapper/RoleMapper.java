package com.monkey.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.monkey.util.PageUtil;
import com.monkey.vo.Role;

/**
 * RoleMapper接口
 * 
 * @author skyer
 */
public interface RoleMapper {

	/**
	 * 查询所有
	 */
	List<Role> findAll();

	/**
	 * 分页查询角色
	 * 
	 * @param pu 分页实体类
	 */
	List<Map<String, Object>> findByPage(@Param("pu") PageUtil pu, @Param("keyword") String keyword);

	/**
	 * 统计角色总数量
	 */
	Long total(@Param("keyword") String keyword);

	/**
	 * 通过主键获取
	 */
	Role findById(Integer id);

	/**
	 * 更新
	 */
	void update(Role role);

	/**
	 * 通过角色名称获取
	 */
	Role findByRoleName(String roleName);

	/**
	 * 添加
	 */
	Integer add(Role role);

	/**
	 * 删除
	 */
	Integer delete(Integer id);

	/**
	 * 通过ID列表获取
	 */
	List<Role> findByIds(@Param("ids") String[] ids);

}
