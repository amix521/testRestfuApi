package org.amix.constant;

/**
 * @description:当前用户
 * @author:@amix.
 * @Date:2018-08-07 22:10
 */
public class TokenConstants {
	
	/**
     * 登录用户
     */
    public final static String CURRENT_USER = "loginUser";
    
    /**
     * 用户菜单
     */
    public final static String USER_MENULIST = "userMenuList";
    
    /**
     * 用户客户号
     */
    public final static String USER_CSTNO = "customerId";
    
	/**
     * 验证码
     */
    public final static String VERIFYCODE = "verifyCode";
    
    /**
     * verifyCode超时时间(秒)
     */
    public final static int VERIFYCODE_EXPSECONDS = 300;
    
    /**
     * 用户token
     */
    public final static String USER_TOKEN = "Access-Token";
    
    /**
     * token加密key
     */
    public final static String TOKEN_SECRETKEY = "amix";
    
    /**
     * token签发者
     */
    public final static String TOKEN_ISSUSER = "www.amix.com";
    
    /**
     * token面向对象
     */
    public final static String TOKEN_SUBJECT = "OSS";
    
    /**
     * token超时时间(毫秒)
     */
    public final static long TOKEN_EXPMILLIS = 3600000;
    
    /**
     * session超时时间(秒)
     */
    public final static int SESSION_EXPSECONDS = 3600;
  
}