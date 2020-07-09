package kr.co.ca;

import java.io.File;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.util.Utils;

@Controller
public class UploadAjaxController {

	private String uploadPath = "C:" + File.separator + "upload";
	
	@ResponseBody
	@RequestMapping(value="/uploadajax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8") //한글안깨지게  속성을 하나 추가 
	public String uploadajax(MultipartHttpServletRequest request) throws Exception {
		
		MultipartFile file = request.getFile("file");
		String originalName = file.getOriginalFilename();
		String saveFileName = Utils.saveFile(originalName, file, uploadPath );
		
		return saveFileName;
	}
	
	@RequestMapping(value="/uploadajax", method = RequestMethod.GET)
	public void uploadajax() {
		
		//path 사용하여 년,월,날짜별 폴더 만든다! 
//		String[] paths = Utils.makeDirName();
//		File f = new File(uploadPath + paths[2]);  //같다
//		if(f.exists()) {
//			return; //이미 오늘자 폴더가 있으면 폴더 안만든다! 
//		} 
//		for(String path : paths) {  //2020년폴더가 제일 먼저 나옴. 
//			File dirPath = new File(uploadPath+path); //같다
//			if(!dirPath.exists()) { //2020폴더있냐?
//				dirPath.mkdir(); 
//			}			
//		}
		
		//폴더에 path를 만들어준다!  ->utils 에 메서드
		//int[] arr = Utils.getDateInfo();
		//String yearPath = File.separator + arr[0]; // \2020
		//String monthPath = yearPath + File.separator + String.format("%02d", arr[1]);  //arr[1] :7 ->07로 만들어야!  ("%02d" 2자리 정수 0으로 채워넣겠다!)
		//String datePath = monthPath + File.separator + String.format("%02d", arr[2]);
	
		
		//업로드 파일관리 여기서 테스트하고  ->utils 에 메서드
		//Calendar cal = Calendar.getInstance();
		//int year = cal.get(Calendar.YEAR);
		//int month = cal.get(Calendar.MONTH)+1;  //month 0월부터....
		//int date = cal.get(Calendar.DATE);
	
		
		
		
	}
	
}
