package ac.uk.shef.cc19grp10.payment;

import ac.uk.shef.cc19grp10.payment.data.UserFactoryImpl;
import ac.uk.shef.cc19grp10.utils.login.LoginInterceptor;
import ac.uk.shef.cc19grp10.utils.login.UserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for the Spring application.
 */
@org.springframework.context.annotation.Configuration
public class Configuration implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// Require login except for some endpoints required for resources, authorization, errors, etc..
		registry.addInterceptor(loginInterceptorBean())
				.excludePathPatterns("/resources/**", "/auth/callback", "/error", "/api/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/");
	}

	// Required for auth utils
	@Bean
	LoginInterceptor loginInterceptorBean() {
		return new LoginInterceptor();
	}

	// Required for auth utils
	@Bean
	UserFactory userFactory() {
		return new UserFactoryImpl();
	}

}
