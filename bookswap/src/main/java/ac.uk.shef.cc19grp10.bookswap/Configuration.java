package ac.uk.shef.cc19grp10.bookswap;

import ac.uk.shef.cc19grp10.bookswap.data.DbUserFactory;
import ac.uk.shef.cc19grp10.utils.payment.PaywallInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ac.uk.shef.cc19grp10.utils.login.LoginInterceptor;
import ac.uk.shef.cc19grp10.utils.login.UserFactory;

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
				.excludePathPatterns("/auth/callback","/error","/img"/* add any other urls that need to be accessible withouh loggin in here*/);
//				.excludePathPatterns("/","/auth/callback","/error","/img"/* add any other urls that need to be accessible withouh loggin in here*/);
		registry.addInterceptor(paywallInterceptorBean())
				.excludePathPatterns("/auth/callback", "/error", "/resources/**", "/api/**", "/payment/callback");

	}


	//required for login system from utils
	@Bean
	LoginInterceptor loginInterceptorBean(){
		return new LoginInterceptor();
	}

	//required for login system from utils
	@Bean
	PaywallInterceptor paywallInterceptorBean(){
		return new PaywallInterceptor();
	}

	//required for login system from utils
	@Bean
	UserFactory userFactory(){
		return new DbUserFactory(); /*Return and instance of UserFactory here*/
	}

}
