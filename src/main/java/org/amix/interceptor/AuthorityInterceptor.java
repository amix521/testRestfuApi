package org.amix.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.amix.constant.InterConstans;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthorityInterceptor extends HandlerInterceptorAdapter{
	
	 @Override
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		 
		//这部分是路径过滤逻辑
	        String path = request.getServletPath();
	        if (path.matches(InterConstans.NO_INTERCEPTOR_PATH)) {
	        	 System.out.println("这里过滤了允许过的路径");
	        	//不需要的拦截直接过
	            return true;
	        } else {
	        	// 不允许访问的资源处理
	            System.out.println("====================================");
	            response.setContentType("application/json; charset=utf-8");
	    		response.getWriter().print("资源不允许访问");
	    		
	            return false;
	        }
	}
}
