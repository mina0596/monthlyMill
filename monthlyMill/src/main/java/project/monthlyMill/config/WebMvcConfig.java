package project.monthlyMill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import project.monthlyMill.interceptor.LoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	// ============ 인터셉터 ===============
	
	private final LoginInterceptor loginInterceptor;
	public WebMvcConfig(LoginInterceptor loginInterceptor) {
		this.loginInterceptor = loginInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/smartStore/**")
				.excludePathPatterns("/smartStore/login")
				.excludePathPatterns("/smartStore/getLoginInfo")
				.excludePathPatterns("/")
				.excludePathPatterns("/favicon.ico")
				.excludePathPatterns("/css")
				.excludePathPatterns("/**/**.js")
				.excludePathPatterns("/img/**")
				.excludePathPatterns("/package/**");
				
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	
	
	// ============ 첨부파일 ===============
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		// 파일 최대크기 5MB로 설정함
		multipartResolver.setMaxUploadSize(31054240);
		return multipartResolver;
	}
}
