package ac.uk.shef.cc19grp10.demo;

import ac.uk.shef.cc19grp10.demo.data.DemoEntity;
import ac.uk.shef.cc19grp10.demo.data.DemoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class DemoController {

    private final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    DemoRepository demoRepo;

    @RequestMapping( value= "/data", method = RequestMethod.GET)
    public ModelAndView allData(){
        return new ModelAndView("demo/index","demoEntities",demoRepo.findAll());
    }

    @RequestMapping( value= "/data/new", method = RequestMethod.GET)
    public ModelAndView newData(){
        return new ModelAndView("demo/create","demoEntity",new DemoEntity());
    }

    @RequestMapping( value= "/data/new", method = RequestMethod.POST)
    public String newData(@Valid @ModelAttribute("demoEntity")DemoEntity entity,
                               BindingResult result, ModelMap model){
        demoRepo.save(entity);
        if (result.hasErrors()) {
            return "error";
        }
        model.addAttribute("someField", entity.getSomeField());
        model.addAttribute("id", entity.getId());
        return "demo/view";
    }

    @RequestMapping( value= "/data/{id}", method = RequestMethod.GET)
    public String getData(@PathVariable("id") long id, Map<String, Object> model){
        model.put("demoEntity",demoRepo.findById(id));
        return "demo/view";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        logger.info("index() executed");
        return "hello";
    }
}
