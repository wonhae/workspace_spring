package kr.co.ca;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.service.BoardService;
import kr.co.util.Utils;

@Controller
public class UploadAjaxController {

	private String uploadPath = "C:" + File.separator + "upload";
	
	@Inject //7.13
	private BoardService bService;
	

	@ResponseBody  //7.13
	@RequestMapping(value="/getAttach/{bno}", method = RequestMethod.GET )
	public List<String> getAttach (@PathVariable("bno") Integer bno){
		
		
		return bService.getAttach(bno);
	}
	
	
	@ResponseBody //7.10 삭제
	@RequestMapping(value="/deletefile", method = RequestMethod.POST )  //이미지의 경우  원본파일도 삭제되게 해야! 
	public String deletefile(String filename) {
		
		filename.replace('/', File.separatorChar);
		
		File f = new File(uploadPath+filename); //filename 넣어주면 어떤폴더에 어떤파일까지 나온다
		 
		//이미지일경우 원본파일 삭제되게!
		int idx = filename.lastIndexOf(".");
		String format = filename.substring(idx+1);
		MediaType mType = Utils.getMediaType(format);
		if(mType != null) {  //이미지파일일경우 -> 원본파일삭제 2020\07\10\s_UUID_oriname ->우리가 원하는건 2020\07\10\UUID_oriname
			String pre = filename.substring(0,12);
			String suf = filename.substring(14);
			String oriname = pre + suf;
			
			File oriFile = new File(uploadPath+oriname);
			oriFile.delete();
		}
		
		f.delete();
		
		
		bService.deleteAttachByFileName(filename);   //첨부파일삭제 
		
		return "success";
	}
	
	
	@ResponseBody //7.10
	@RequestMapping(value="/displayfile", method = RequestMethod.GET)  
	public ResponseEntity<byte[]> displayfile(String filename){  //result 가 filename,  이미지가 가진걸 바이트 배열로 만들어준다! 헤더변경해줘야해서 ResponseEntity<byte[]>이렇게 한 것! 보통이걸추천
		ResponseEntity<byte[]> entity = null;
		
		InputStream in = null;
		
		try {
			int idx = filename.lastIndexOf(".");			
			String format = filename.substring(idx + 1);			
			MediaType mType = Utils.getMediaType(format);  //여기까지가 이미지파일 여부만 판단하는것! 

			in = new FileInputStream(uploadPath + filename);  // 썸네일, 스트링 연결해주는 코드.   이번엔 문자열로 넣어봤음~~~~  이전엔 fileclass 이용했는데~ 실무에서는 fileclass 이용함.	
			
			HttpHeaders headers = new HttpHeaders();// 헤더에 마임타입 지정해준거
			
			if(mType != null) {
				headers.setContentType(mType); //이미지파일의 경우만 값을 넘겨준다. 
				
			} else { //이쪽부분이 다운로드~~~ 
				idx = filename.indexOf("_");   //a.txt업로드 ->내부관리는 uuid_orgname~~ ->다운로드시 a.txt 로 되게!
				String originalName = filename.substring(idx + 1);  
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  //다운로드 받게 해주는 마임 타입
				headers.add("Content-Disposition", "attachment;filename=\""+new String(originalName.getBytes("UTF-8"),"ISO-8859-1")); // \" ->데이터로서 큰따옴표를 넣는것이다.
				

			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.OK);  // 썸네일바이트데이터, 헤더정보, 통신상태코드  //img src에 소스에다가 데이터 넣어주는 코드 
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST); //실패시 통신 상태코드만 넘겨준다.
		} finally {
			try {
				if(in != null) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return entity;
	}
	
	
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
