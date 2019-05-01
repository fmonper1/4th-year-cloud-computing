package ac.uk.shef.cc19grp10.payment.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

	private Logger logger = LoggerFactory.getLogger(IndexController.class);

	@GetMapping
	public ModelAndView getAccountForCurrentUser()
	{
		return new ModelAndView("index");
	}
}
