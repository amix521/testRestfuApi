package org.amix.util;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Value;

/**
 * 加密时使用类，需要配黑配置文件加密盐jasypt.encryptor.password使用
 * @author Administrator
 *
 */
public class JasyptTest {
	
	@Value("${jasypt.encryptor.password}")
	String jasyptKey;
	
	static BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
	
	public JasyptTest(){
		textEncryptor.setPassword(jasyptKey);
	}
	
	public static String JasyptTest(String in){
		return textEncryptor.encrypt(in);
	}
	
}