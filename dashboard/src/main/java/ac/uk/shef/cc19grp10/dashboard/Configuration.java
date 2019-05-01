package ac.uk.shef.cc19grp10.dashboard;

import ac.uk.shef.cc19grp10.dashboard.data.DbUserFactory;
import ac.uk.shef.cc19grp10.utils.login.LoginInterceptor;
import ac.uk.shef.cc19grp10.utils.login.UserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <Doc here>
 * <p>
 * Created on 08/04/2019.
 */
@org.springframework.context.annotation.Configuration
public class Configuration implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//setup login interceptor from utils
		//(note that /auth/callback and /auth are the minimum ignored paths for login to work correctly)
		registry.addInterceptor(loginInterceptorBean())
				.excludePathPatterns("/","/auth/callback","/error","/debug/**","/resources/**");
	}


	//required for login system from utils
	@Bean
	LoginInterceptor loginInterceptorBean(){
		return new LoginInterceptor();
	}

	//required for login system from utils
	@Bean
	UserFactory userFactory(){
		return new DbUserFactory();
	}
}
