package ac.uk.shef.cc19grp10.dashboard;

import ac.uk.shef.cc19grp10.dashboard.data.Application;
import ac.uk.shef.cc19grp10.dashboard.data.ApplicationRepository;
import ac.uk.shef.cc19grp10.dashboard.data.Deployment;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/api/app/")
public class ApplicationDataController {

    private final Logger logger = LoggerFactory.getLogger(ApplicationDataController.class);

    @Autowired
	ApplicationRepository appRepo;


    @GetMapping("{id}/image")
    public void index(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
		Application app = appRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		Deployment deployment = app.getDeployment();
		if(deployment == null){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		File file = new File(deployment.getImagePath());
		FileInputStream fileInputStream = new FileInputStream(file);
		IOUtils.copy(fileInputStream,response.getOutputStream());
		response.flushBuffer();
    }
}
