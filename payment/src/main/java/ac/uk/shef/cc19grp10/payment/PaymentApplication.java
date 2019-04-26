package ac.uk.shef.cc19grp10.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"ac.uk.shef.cc19grp10"})
public class PaymentApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PaymentApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

}
