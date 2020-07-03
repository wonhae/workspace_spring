package kr.co.util;

import java.io.UnsupportedEncodingException;

public class Utils {
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
