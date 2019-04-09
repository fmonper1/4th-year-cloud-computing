package ac.uk.shef.cc19grp10.dashboard;

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

}
