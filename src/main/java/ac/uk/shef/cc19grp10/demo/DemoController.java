package ac.uk.shef.cc19grp10.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @RequestMapping("/")
    public String index(){
        return "Hello";
    }
}
