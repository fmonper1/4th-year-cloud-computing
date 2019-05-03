package ac.uk.shef.cc19grp10.tutorFinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages ={"ac.uk.shef.cc19grp10.tutorFinder", "ac.uk.shef.cc19grp10.utils.login"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}