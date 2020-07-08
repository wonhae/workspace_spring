package kr.co.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class Utils {
	
	//자주쓰는거 메서드 처리
	public static String saveFile(String originalName, MultipartFile file) throws Exception {
		String newName = Utils.makeNewName(originalName);		
		File target = new File("C:"+File.separator + "upload", newName);   //자기이름쓰면 덮어쓰기 되서 안됨 //file.io
		FileCopyUtils.copy(file.getBytes(),target);//파일업로드 하는 코드  file.getBytes를 target(경로)에 넣는다. 
		return newName;
	}
	
	
	// 7/8 upload관련 새로운 이름 넣어주는 메서드  
	public static String makeNewName(String originalName) {
		UUID uid = UUID.randomUUID();
		String newName = uid.toString()+"_"+originalName;  //toString에는 _안들어간다.
		
		return newName;
	}
	
	
	public static String toKor(String msg) {  //한글 인코딩 필요할때마다 메서드 호출   //클라이언트가 한글데이터 보낼만한 곳에 넣어주기!. 
		try {
			return new String(msg.getBytes("8859-1"), "UTF-8");  //msg를 바이트형태로만든다. 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";  //null 넣어도됨
 		}
	}
	
	
	
	
}
