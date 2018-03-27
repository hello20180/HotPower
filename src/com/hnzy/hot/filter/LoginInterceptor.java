package com.hnzy.hot.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hnzy.hot.home.service.RoleMenuService;
import com.hnzy.hot.home.service.Impl.RoleMenuServiceImpl;
import com.hnzy.hot.xtgl.pojo.User;

public class LoginInterceptor implements HandlerInterceptor {

	// handler执行完成之后调用这个方法
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	// handler执行之后，ModelAndView返回之前调用这个方法
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	// handler执行之前调用这个方法
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 获取请求的URL
		StringBuffer url = request.getRequestURL();

		String path = url.substring(url.lastIndexOf("/"));
		// URL:login.jsp是公开的；这个demo是除了login.jsp是可以公开访问的，其他的URL都进行拦截控制
		if (url.indexOf("login.action") > 0 && path.indexOf(".action") != -1 && !"/login.action".equals(path)) {
			return true;
		}
		// 判断是否是ajax请求
		if (request.getHeader("x-requested-with") != null
				&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
			// 如果是ajax请求响应头会有，x-requested-with
			return true;
		}

		if (request.getSession().getAttribute("userName") != null) {
			if (url.toString().contains("/home/")) {

				User user = (User) request.getSession().getAttribute("usermenu");
				RoleMenuService rms = new RoleMenuServiceImpl();
				String action = path.substring(1, path.lastIndexOf("."));
				//
				if ("index".equals(action)) {
					request.getRequestDispatcher("/WEB-INF/jsp/home/index.jsp").forward(request, response);
					return true;
				} else if (rms.isAccessible(user, 1) && "foots".equals(action)) {
					request.getRequestDispatcher("/WEB-INF/jsp/home/foots.jsp").forward(request, response);
					return true;
				} else if (rms.isAccessible(user, 2) && "kfgl".equals(action)) {
					request.getRequestDispatcher("/WEB-INF/jsp/home/kfgl.jsp").forward(request, response);
					return true;
				} else if (rms.isAccessible(user, 3) && "tjfx".equals(action)) {
					request.getRequestDispatcher("/WEB-INF/jsp/home/tjfx.jsp").forward(request, response);
					return true;
				} else if (rms.isAccessible(user, 4) && "sjbb".equals(action)) {
					request.getRequestDispatcher("/WEB-INF/jsp/home/sjbb.jsp").forward(request, response);
					return true;
				} else if (rms.isAccessible(user, 5) && "xxgl".equals(action)) {
					request.getRequestDispatcher("/WEB-INF/jsp/home/xxgl.jsp").forward(request, response);
					return true;
				} else if (rms.isAccessible(user, 6) && "hrzgl".equals(action)) {
					request.getRequestDispatcher("/WEB-INF/jsp/home/hrzgl.jsp").forward(request, response);
					return true;
				} else if (rms.isAccessible(user, 7) && "nhfx".equals(action)) {
					request.getRequestDispatcher("/WEB-INF/jsp/home/nhfx.jsp").forward(request, response);
					return true;
				} else if (rms.isAccessible(user, 8) && "xtsz".equals(action)) {
					request.getRequestDispatcher("/WEB-INF/jsp/home/xtsz.jsp").forward(request, response);
					return true;
				} else if (rms.isAccessible(user, 45) && "sbgl".equals(action)) {
					request.getRequestDispatcher("/WEB-INF/jsp/home/sbgl.jsp").forward(request, response);
					return true;
				} else {
					request.getRequestDispatcher("/WEB-INF/jsp/home/NewFile.jsp").forward(request, response);
					return true;
				}

			}
				request.getRequestDispatcher("/WEB-INF/jsp/home/login.jsp").forward(request, response);
		}
		// 不符合条件，跳转到登录界面
		request.getRequestDispatcher("/WEB-INF/jsp/home/login.jsp").forward(request, response);

		return false;
	}

}
