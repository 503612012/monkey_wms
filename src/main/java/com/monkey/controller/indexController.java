package com.monkey.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 主页
 * 
 * @author skyer
 */
@Controller
public class indexController extends BaseController {

	private final static Logger L = Logger.getLogger(indexController.class);

	/**
	 * 去到系统主页面
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		try {
			ModelAndView mv = new ModelAndView("/index");
			return mv;
		} catch (Exception e) {
			L.error("--------------------------", e);
			e.printStackTrace();
		}
		return null;
	}

}
