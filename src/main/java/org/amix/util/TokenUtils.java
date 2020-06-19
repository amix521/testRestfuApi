package org.amix.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

import org.amix.constant.TokenConstants;

/**
 * token工具类
 * 
 * @author amix
 */
public class TokenUtils {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 生成token
	 * 
	 * @param inputStr
	 *            需要签名字符串
	 * @return
	 */
	public static String createJwtToken(String inputStr) {
		
		return createJwtToken(inputStr, TokenConstants.TOKEN_ISSUSER, TokenConstants.TOKEN_SUBJECT, TokenConstants.TOKEN_EXPMILLIS);
	}
	
	/**
	 * 生成token
	 * 
	 * @param inputStr
	 *            需要签名字符串
	 * @param expMillis
	 *            超时时间 (毫秒),0不超时
	 * @return
	 */
	public static String createJwtToken(String inputStr, long expMillis) {

		return createJwtToken(inputStr, TokenConstants.TOKEN_ISSUSER, TokenConstants.TOKEN_SUBJECT, expMillis);
	}

	/**
	 * 生成Token
	 * 
	 * @param inputStr
	 *            需要签名字符串
	 * @param issuer
	 *            签发者
	 * @param subject
	 *            面向用户
	 * @param expMillis
	 *            超时时间 (毫秒),0不超时
	 * @return
	 */
	public static String createJwtToken(String inputStr, String issuer, String subject, long expMillis) {

		// 签名算法 ，将对token进行签名
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		// 生成签发时间
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		// 通过秘钥签名JWT
		byte[] apiKeyBytes = DatatypeConverter.parseBase64Binary(TokenConstants.TOKEN_SECRETKEY);
		Key signingKey = new SecretKeySpec(apiKeyBytes, signatureAlgorithm.getJcaName());

		JwtBuilder builder = Jwts.builder().claim(TokenConstants.USER_CSTNO, inputStr).setIssuer(issuer)
				.setSubject(subject).setIssuedAt(now).signWith(signatureAlgorithm, signingKey);

		if (expMillis > 0) {
			expMillis = nowMillis + expMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		return builder.compact();

	}
	
	/**
	 * 生成VerifyToken
	 * 
	 * @param inputStr
	 *            需要签名字符串
	 * @param issuer
	 *            签发者
	 * @param subject
	 *            面向用户
	 * @param expMillis
	 *            超时时间 (毫秒),0不超时
	 * @return
	 */
	public static String createVerifyToken(String inputStr, long expMillis) {

		// 签名算法 ，将对token进行签名
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		// 生成签发时间
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		// 通过秘钥签名JWT
		byte[] apiKeyBytes = DatatypeConverter.parseBase64Binary(TokenConstants.TOKEN_SECRETKEY);
		Key signingKey = new SecretKeySpec(apiKeyBytes, signatureAlgorithm.getJcaName());
		JwtBuilder builder = Jwts.builder().setId(inputStr).setIssuer(TokenConstants.TOKEN_ISSUSER).setSubject(TokenConstants.TOKEN_SUBJECT).setIssuedAt(now).signWith(signatureAlgorithm, signingKey);

		if (expMillis > 0) {
			expMillis = nowMillis + expMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		return builder.compact();

	}
	
	// 验证解析token
	public static Claims verifyToken(String tokenStr) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(TokenConstants.TOKEN_SECRETKEY))
				.parseClaimsJws(tokenStr).getBody();
		return claims;
	}

	public static void main(String[] args) {
		// System.out.println(TokenUtils.createJwtToken("admin",1));
		String ss = TokenUtils.createJwtToken("admin", -1);
		// try{
		// Thread.sleep(3000);
		// }catch(Exception e){
		//
		// }
		System.out.println(verifyToken(ss));
	}
}
