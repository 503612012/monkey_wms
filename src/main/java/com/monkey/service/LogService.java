package com.monkey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.mapper.LogMapper;
import com.monkey.vo.Log;

/**
 * 日志服务层
 * 
 * @author skyer
 */
@Service
public class LogService {

	@Autowired
	private LogMapper logMapper;
	
	/**
	 * 添加
	 */
	public void add(Log log) {
		logMapper.add(log);
	}

}
