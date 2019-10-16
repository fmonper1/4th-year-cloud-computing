package ac.uk.shef.cc19grp10.payment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for the index user page.
 */

@Controller
@RequestMapping("/")
public class IndexController {

	@GetMapping
	public ModelAndView getIndex()
	{
		return new ModelAndView("redirect:/account");
	}

}
