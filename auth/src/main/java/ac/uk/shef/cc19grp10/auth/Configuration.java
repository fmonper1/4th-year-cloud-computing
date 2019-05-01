package ac.uk.shef.cc19grp10.auth;

import ac.uk.shef.cc19grp10.auth.security.HashingStrategy;
import ac.uk.shef.cc19grp10.auth.security.PBKDF2HashingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * <Doc here>
 * <p>
 * Created on 08/04/2019.
 */
@org.springframework.context.annotation.Configuration
public class Configuration implements WebMvcConfigurer {
	@Bean
	public HashingStrategy hashingStrategy(){
		return new PBKDF2HashingStrategy();
	}

	@Bean
	LoadUserInterceptor loadUserInterceptorBean() {
		return new LoadUserInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loadUserInterceptorBean());
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/auth");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(fromAccessTokenResolverBean());
	}

	@Bean
	FromAccessTokenResolver fromAccessTokenResolverBean() {
		return new FromAccessTokenResolver();
	}
}
