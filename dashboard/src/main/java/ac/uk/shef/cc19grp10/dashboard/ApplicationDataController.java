package ac.uk.shef.cc19grp10.dashboard;

import ac.uk.shef.cc19grp10.dashboard.data.Application;
import ac.uk.shef.cc19grp10.dashboard.data.ApplicationRepository;
import ac.uk.shef.cc19grp10.dashboard.data.Deployment;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Controller
@RequestMapping("/api/app/")
public class ApplicationDataController {

	private static final HashMap<String,String> MIME_TABLE;
	static{
		HashMap<String,String> mimeTable = new HashMap<>();
		mimeTable.put(".png", MediaType.IMAGE_PNG_VALUE);
		mimeTable.put(".jpg", MediaType.IMAGE_JPEG_VALUE);
		mimeTable.put(".svg", "image/svg+xml");
		MIME_TABLE = mimeTable;
	}
	private final Logger logger = LoggerFactory.getLogger(ApplicationDataController.class);

    @Autowired
	ApplicationRepository appRepo;


    @GetMapping("{id}/image")
    public void index(@PathVariable("id") long id, HttpServletResponse response, HttpServletRequest request) throws IOException {
		logger.info("Loading image, id: {}",id);
		Application app = appRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		Deployment deployment = app.getDeployment();
		logger.info("Loading image, deployment: {}",deployment);
		if(deployment == null){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		String imagePath = deployment.getImagePath();
		logger.info("Loading image, path: {}",imagePath);
		if (imagePath == null) {
			URL resourceUrl = request.getServletContext().getResource("/resources/img/default_image.svg");
			if (resourceUrl != null) {
				imagePath = resourceUrl.getPath();
			}
		}
		logger.info("Loading image, path: {}", imagePath);
		if(imagePath == null){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		lookupContentType(imagePath).ifPresent(contentType -> {
			response.setHeader(HttpHeaders.CONTENT_TYPE,contentType);
		});
		File file = new File(imagePath);
		FileInputStream fileInputStream = new FileInputStream(file);
		IOUtils.copy(fileInputStream,response.getOutputStream());
		response.flushBuffer();
    }

	private Optional<String> lookupContentType(String imagePath) {
    	return getFileExtension(imagePath).flatMap(ext -> Optional.ofNullable(MIME_TABLE.get(ext)));
	}

	private Optional<String> getFileExtension(String imagePath) {
		int extensionIndex = imagePath.lastIndexOf(".");
		if (extensionIndex == -1){
			return Optional.empty();
		}
		return Optional.of(imagePath.substring(extensionIndex));
	}
}
