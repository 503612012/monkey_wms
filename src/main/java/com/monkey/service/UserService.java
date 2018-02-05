package com.monkey.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.monkey.mapper.UserMapper;
import com.monkey.util.PageUtil;
import com.monkey.vo.User;

/**
 * 用户服务层
 * 
 * @author skyer
 */
@Service
public class UserService {

	@Autowired
	private MenuService menuService;
	@Autowired
	private UserMapper userMapper;

	/**
	 * 分页查询用户
	 * 
	 * @param pu 分页实体类
	 */
	public JSONArray findByPage(Integer pageNum, Integer pageSize, String keyword) {
		PageUtil pu = new PageUtil(pageNum, pageSize);
		List<Map<String, Object>> list = userMapper.findByPage(pu, keyword);
		JSONArray result = new JSONArray();
		for (Map<String, Object> map : list) {
			result.add(map);
		}
		return result;
	}

	/**
	 * 通过用户名查询
	 * 
	 * @param userName 用户名
	 */
	public User findByUserName(String userName) {
		return userMapper.findByUserName(userName);
	}

	/**
	 * 通过用户名查询该用户的角色
	 * 
	 * @param userName 用户名 
	 */
	public Set<String> findRoles(String userName) {
		return userMapper.findRoles(userName);
	}

	/**
	 * 通过用户名查询该用户的权限
	 * 
	 * @param userName 用户名
	 */
	public Set<String> findPermissions(String userName) {
		User user = userMapper.findByUserName(userName);
		Integer roleId = user.getRoleId();
		Set<String> permissions = menuService.findPermissionByRoleId(roleId);
		return permissions;
	}

	/**
	 * 统计用户总数量
	 */
	public Long total(String keyword) {
		return userMapper.total(keyword);
	}

	/**
	 * 添加用户
	 */
	public Integer add(User user) {
		return userMapper.add(user);
	}

	/**
	 * 删除用户
	 */
	public Integer delete(Integer id) {
		return userMapper.delete(id);
	}

	/**
	 * 修改用户
	 */
	public void update(User user) {
		userMapper.update(user);
	}

	/**
	 * 通过主键查询
	 */
	public User findById(Integer id) {
		return userMapper.findById(id);
	}

}
