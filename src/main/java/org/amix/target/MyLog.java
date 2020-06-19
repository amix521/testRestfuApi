package org.amix.target;

import java.lang.annotation.*;

/**
 * 这里做自定义注解标签，当然只做注解标签的定义，具体需要操作的标签的处理要在aop或者通过拦截器的handler来反射获取后处理，例如：
 * 		HandlerMethod handlerMethod = (HandlerMethod) handler;
 *		Method method = handlerMethod.getMethod();
 *		LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
 *
 * @author Administrator
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLog { 
	
	
	/**
	 * 日志切面类型。前，后，环绕
	 * @return
	 */
	public int aopType() default 1;
}