package ac.uk.shef.cc19grp10.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public ModelAndView afterLogin(@RequestParam(value = "redirect",required=false) String redirect){
        if (redirect != null){
            return new ModelAndView(new RedirectView(redirect,false));
        }
        return new ModelAndView(new RedirectView("/",true));
    }
}
