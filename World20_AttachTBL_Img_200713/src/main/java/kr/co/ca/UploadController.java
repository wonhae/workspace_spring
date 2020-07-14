package kr.co.ca;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import kr.co.util.Utils;



@Controller
public class UploadController {

	private String uploadPath = "C:" + File.separator + "upload";
	
	@RequestMapping(value="/uploadform", method=RequestMethod.POST)
	public void uploadform(MultipartHttpServletRequest request, Model model) throws Exception {	
		MultipartFile file = request.getFile("file");
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		
		String originalName = file.getOriginalFilename();
		
		String fileName = Utils.saveFile(originalName, file, uploadPath); //이 메서드는 주석친 부분!! 
		//String newName = Utils.makeNewName(originalName);		
		//File target = new File("C:"+File.separator + "upload", newName);   //자기이름쓰면 덮어쓰기 되서 안됨 //file.io
		//FileCopyUtils.copy(file.getBytes(),target);//파일업로드 하는 코드  file.getBytes를 target(경로)에 넣는다.
		model.addAttribute("fileName", fileName);
	}
	
	
	@RequestMapping(value="/uploadform", method=RequestMethod.GET)
	public void uploadform() {		
	}
	
}
