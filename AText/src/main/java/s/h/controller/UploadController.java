package s.h.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import s.h.util.Utils;

@Controller
public class UploadController {
	
	@RequestMapping(value="/uploadform")
	public void uploadform(MultipartHttpServletRequest request, Model model) {
		MultipartFile file = request.getFile("file");
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		
		String orgName = file.getOriginalFilename();
		String newName = Utils.makeNewName(orgName);
		File target = new File("C:"+File.separator+"upload");
		
		
	}
	
	
	@RequestMapping(value="/uploadform")
	public void uploadform() {
	
	}
}
