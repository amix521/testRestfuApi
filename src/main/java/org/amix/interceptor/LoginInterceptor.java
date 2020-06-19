package org.amix.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.amix.target.LoginRequired;
import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	/**
	 * 在请求处理之前进行调用（Controller方法调用之前） 基于URL实现的拦截器
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 反射获取方法上的LoginRequred注解，判断登录的逻辑

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
		if (loginRequired != null) {
			// 这写你拦截需要干的事儿，比如取缓存，SESSION，权限判断等
			System.out.println("====================================");
			return true;
		}
		// 有LoginRequired注解说明需要登录，提示用户登录
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print("你访问的资源需要登录");
		return false;
	}
}
