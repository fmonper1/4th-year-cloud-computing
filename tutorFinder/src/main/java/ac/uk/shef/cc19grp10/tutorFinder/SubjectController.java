package ac.uk.shef.cc19grp10.tutorFinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import ac.uk.shef.cc19grp10.tutorFinder.Subject;
import ac.uk.shef.cc19grp10.tutorFinder.SubjectRepository;

import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class SubjectController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    //private SubjectRepository subjectRep;
    private SubjectRepository subjectRepository;

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    private static List<Subject> subjects = new ArrayList<Subject>();

//    @RequestMapping( value= "/index", method = RequestMethod.POST)
//    public String showSubject(@RequestParam (value = "searchSubject", required = false) String subject, Model model) {
//        subjects.clear();
//        subjectRepository.findByName(subject);
//        if(subjects.isEmpty()){
//            logger.info("its empty broooooo");
//        }
//
//        model.addAttribute("subjectResults", subjects);
//
//        return "index";
//    }


}