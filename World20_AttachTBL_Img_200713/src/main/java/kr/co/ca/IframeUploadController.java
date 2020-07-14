package kr.co.ca;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.util.Utils;

@Controller
public class IframeUploadController {
	
	private String uploadPath = "C:" + File.separator + "upload";

	@RequestMapping(value="/iuploadform", method = RequestMethod.POST) //넘겨준거 저장
	public String iuploadform(MultipartHttpServletRequest request, Model model) throws Exception {
		
		MultipartFile file = request.getFile("file");
		String originalName = file.getOriginalFilename();
		
		String newName = Utils.saveFile(originalName, file, uploadPath);
		model.addAttribute("newName", newName);
		
		return "result"; //result.jsp로간다. 
	}
	
	@RequestMapping(value="/iuploadform", method = RequestMethod.GET)
	public void iuploadform() {
		
	}
}
