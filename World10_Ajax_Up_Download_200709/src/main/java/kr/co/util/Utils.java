package kr.co.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class Utils {
	
	//path 사용하여 년,월,날짜별 폴더 만든다! \2020\07\09폴더가 진짜로 생기게! 
	public static String makeDir(String uploadPath) {
		String[] paths = Utils.makeDirName();
		File f = new File(uploadPath + paths[2]);  //같다=
		if(f.exists()) {
			return paths[2]; //이미 오늘자 폴더가 있으면 폴더 안만든다! 
		} 
		for(String path : paths) {  //2020년폴더가 제일 먼저 나옴. 
			File dirPath = new File(uploadPath+path); //같다=
			if(!dirPath.exists()) { //2020폴더있냐?
				dirPath.mkdir(); 
			}			
		}
		return  paths[2]; // 이게 datepath  \2020\07\09이거!  여기에 업로드 하는 파일을ㄹ 저장!!! 
	}
	
	//폴더에 path를 만들어준다!  7/9 -> \2020\07\09이렇게 나오게! 
	public static String[] makeDirName() {
		int[] arr = Utils.getDateInfo();
		String yearPath = File.separator + arr[0]; // \2020
		String monthPath = yearPath + File.separator + String.format("%02d", arr[1]);  //arr[1] :7 ->07로 만들어야!  ("%02d" 2자리 정수 0으로 채워넣겠다!)
		String datePath = monthPath + File.separator + String.format("%02d", arr[2]);
		
		String[] paths = {yearPath, monthPath, datePath};
		return paths;
	}
	
	//날짜  7/9 
	public static int[] getDateInfo() {
		Calendar cal = Calendar.getInstance();

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1; // month 0월부터....
		int date = cal.get(Calendar.DATE);
		
		int[] arr = {year, month, date};
		
		return arr;
	}
	
	//**저장  7/9 파라미터 하나 추가   ->makeDir 수정하면 uploadPath 수정해줘야 한다!   
	public static String saveFile(String originalName, MultipartFile file, String uploadPath) throws Exception {
		
		String newName = Utils.makeNewName(originalName);	
		String datePath = Utils.makeDir(uploadPath);
		
		File target = new File(uploadPath+datePath, newName);   //자기이름쓰면 덮어쓰기 되서 안됨 //file.io  //수정함. 
		
		FileCopyUtils.copy(file.getBytes(),target);  //파일업로드 하는 코드  file.getBytes를 target(경로)에 넣는다. 
		
		boolean isImgFile = isImg(originalName);  // filename = originalName or newName
		if(isImgFile) {
			 return makeThumbnail(uploadPath, datePath , newName);  //  /로 반환하도록!! 경로랑 파일명 다 보내주려고. 
			//System.out.println("썸네일을 만듦");
		} else { //아이콘만듦. 
			//System.out.println("썸네일을 안만듦"); 
			String beforeChangeName = datePath + File.separator + newName;
			return beforeChangeName.replace(File.separatorChar, '/');
		}
		
	}
	
	//썸네일 만드는 메서드  (원본파일 어딨는지 알아야 만들 수 있음: 폴더정보(C의 업로드..거기or project - resources 의 업르드 폴더 )/ 이 안에 년월일폴더/ saveFile method의  newName
	public static String makeThumbnail(String uploadPath, String datePath , String newName) throws Exception {
		File f1 = new File(uploadPath + datePath, newName);   //원본파일 어딘지 알려주는 파일객체 
		//더블 버퍼링기법 : 버퍼 2번 사용하여 이미지 복사하는 작업 ,
		BufferedImage sourceImg = ImageIO.read(f1);   // 이미지를 ram(buffer)에다 저장함  //더블 버퍼링기법 : 버퍼 2번 사용하여 이미지 복사하는 작업 , 예외날려줌
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_EXACT, 100);  //이미지를 ram(buffer)에다 저장함 //원본 이미지를 100 크기로 만든다!!! 
		
		String thumbnailName = uploadPath + datePath + File.separator + "s_" +newName;   //썸네일 클릭하면 원본 나오도록!! 규칙!!  //썸네일에서 s_만 제거하면 원본이 나옴 
		File newFile = new File(thumbnailName);   //위에것 이용 파일객체
		
		int idx = newName.lastIndexOf(".");  //확장자 newName 에서 끄집어냄
		String format = newName.substring(idx + 1).toUpperCase(); // png ->대문자됨! 
		
		ImageIO.write(destImg, format, newFile);  //썸네일을 기본 저장소로!!! destImg를 format 형태로 newFile에 저장! 
		
		
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/'); //   /2020/07/09/s_uuid이용이름_오리지널네임.png  ->이렇게 썸네일 이름 바꿔주기 
	}
	
	
	//파일 업로드하고나서 이미지파일이냐 아니냐(확장자만 떼옴)에 따라 썸네일 만들지 결정!! 
	public static boolean isImg(String filename) {
		
		int idx = filename.lastIndexOf(".");  // .다음부터 잘라내기! 
		String format = filename.substring(idx + 1); // . 은 안포함
		
		Map<String, MediaType> map = new HashMap<String, MediaType>();  // MediaType =>org.springframework.http 이걸로!!
		map.put("JPG", MediaType.IMAGE_JPEG);
		map.put("JPEG", MediaType.IMAGE_JPEG);
		map.put("PNG", MediaType.IMAGE_PNG);
		map.put("GIF", MediaType.IMAGE_GIF);
	
		MediaType mType = map.get(format.toUpperCase());  // 위어꺼랑 대문자 맞춰주려고   //map에 저장된 값을 넘겨주는게 get! (키값을찾아서)
		if(mType != null) {
			return true;
		} else {
			return false;			
		}
	}
	
	
	
	// 7/8 upload관련 새로운 이름 넣어주는 메서드  
	public static String makeNewName(String originalName) {
		UUID uid = UUID.randomUUID();
		String newName = uid.toString()+"_"+originalName;  //toString에는 _안들어간다.
		
		return newName;
	}
	
	//한글 인코딩 필요할때
	public static String toKor(String msg) {   //클라이언트가 한글데이터 보낼만한 곳에 넣어주기!. 
		try {
			return new String(msg.getBytes("8859-1"), "UTF-8");  //msg를 바이트형태로만든다. 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";  //null 넣어도됨
 		}
	}
	
	
	
	
}
