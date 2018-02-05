package com.monkey.mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.monkey.util.PageUtil;
import com.monkey.vo.User;

/**
 * UserMapper接口
 * 
 * @author skyer
 */
public interface UserMapper {

	/**
	 * 分页查询用户
	 * 
	 * @param pu 分页实体类
	 */
	List<Map<String, Object>> findByPage(@Param("pu") PageUtil pu, @Param("keyword") String keyword);

	/**
	 * 通过用户名查询
	 * 
	 * @param userName 用户名
	 */
	User findByUserName(String userName);

	/**
	 * 通过用户名查询该用户的角色
	 * 
	 * @param userName 用户名
	 */
	Set<String> findRoles(String userName);

	/**
	 * 通过用户名查询该用户的权限
	 * 
	 * @param userName 用户名
	 */
	Set<String> findPermissions(String userName);

	/**
	 * 统计用户总数量
	 */
	Long total(@Param("keyword") String keyword);

	/**
	 * 添加用户
	 */
	Integer add(User user);

	/**
	 * 删除用户
	 */
	Integer delete(Integer id);

	/**
	 * 通过主键查询
	 */
	User findById(Integer id);

	/**
	 * 更新
	 */
	void update(User userInDb);

}
