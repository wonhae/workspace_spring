package kr.co.ca;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;



@Controller
public class UploadController {
	
	@RequestMapping(value="/uploadform", method=RequestMethod.POST)
	public void uploadform(MultipartRequest request) throws Exception {	
		MultipartFile file = request.getFile("file");
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		
		//file.io
		File target = new File("C:"+File.separator + "upload", file.getOriginalFilename());//원래 자기이름쓰면 안됨 
		FileCopyUtils.copy(file.getBytes(),target);//파일업로드 하는 코드  file.getBytes를 target(경로)에 넣는다. 
	}
	
	@RequestMapping(value="/uploadform", method=RequestMethod.GET)
	public void uploadform() {		
	}
	
}
