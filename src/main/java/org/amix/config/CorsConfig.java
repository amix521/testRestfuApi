package org.amix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author amix 解决静态资源跨域问题
 *
 */
@Configuration
public class CorsConfig {

	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		// 允许任何域名访问
		corsConfiguration.addAllowedOrigin("*");
		// 允许任何header访问
		corsConfiguration.addAllowedHeader("*");
		// 允许任何方法访问
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.setAllowCredentials(true);
		return corsConfiguration;
	}

	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration corsConfiguration = new CorsConfiguration();
		/* 是否允许请求带有验证信息 */
		corsConfiguration.setAllowCredentials(true);
		/* 允许访问的客户端域名 */
		corsConfiguration.addAllowedOrigin("*");
		/* 允许服务端访问的客户端请求头 */
		corsConfiguration.addAllowedHeader("*");
		/* 允许访问的方法名,GET POST等 */
		corsConfiguration.addAllowedMethod("*");
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
	
//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity.addFilterBefore(corsFilter(), ChannelProcessingFilter.class);
//	}
}