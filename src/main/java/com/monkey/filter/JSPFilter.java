package com.monkey.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monkey.constant.Constant;
import com.monkey.vo.User;

/**
 * jsp页面过滤器，防止直接访问jsp页面
 * 
 * @author skyer
 */
public class JSPFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String servletPath = req.getServletPath();
		if (servletPath.endsWith("jsp")) {
			User user = (User) req.getSession().getAttribute(Constant.CURRENT_USER);
			if (user == null) {
				resp.sendRedirect(getDomain(req) + "sys/loginPage.html");
				return;
			}
		}
		chain.doFilter(req, resp);
		return;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	/**
	 * 获得域名
	 */
	protected String getDomain(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		return basePath;
	}

}
