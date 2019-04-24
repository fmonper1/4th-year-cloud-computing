package ac.uk.shef.cc19grp10.dashboard;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <Doc here>
 * <p>
 * Created on 19/04/2019.
 */
@RestController
@RequestMapping("/debug")
public class DebugController {
	@Autowired
	private ListableBeanFactory beanFactory;

	@GetMapping("/controllers")
	public List<String> listControllers(){
		List<String> controllers = new ArrayList<>();
		for (final Object bean: beanFactory.getBeansWithAnnotation(
				Controller.class).values())
		{
			controllers.add(bean.getClass().getName());
		}
		return controllers;
	}
}
