package kr.co.ca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.domain.MemberDTO;

@Controller
public class AjaxController {

	@ResponseBody
	@RequestMapping(value = "at4", method = RequestMethod.POST)
	public List<Map<String, Object>> at4(@RequestParam Map<String, Object> map) throws Exception{  //String listStr -> [{"id":"m001","name":"kim","age":22},{"id":"m002","nam
		System.out.println(map); //{listStr=[{"id":"m001","name":"kim","age
		String jsonStr = map.get("listStr").toString();  //json형태로 나온다
		
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> list = mapper.readValue(jsonStr, new TypeReference<ArrayList<Map<String, Object>>>() { //f 눌러서 throws~ 
		});
		
		for(Map<String, Object> m : list) {
			System.out.println("::::::::");
			System.out.println(m.get("id"));
			System.out.println(m.get("name"));
			System.out.println(m.get("age"));
			System.out.println("::::::::");
		}
		return list;
	}
	
	@RequestMapping(value = "at4", method = RequestMethod.GET)
	public void at4() {
		
	}
	
	
	//3. 데이터가 여러개일떄! 
	@ResponseBody
	@RequestMapping(value = "at3", method = RequestMethod.POST)
	public MemberDTO at3(MemberDTO dto) {
		//보통 여기서 db작업 한다. 
		return dto;  //json : 자바스크립트에서 객체를 표기할수 있는 또다른 방법  //{"id":"m007","name":"lee","age":33} 이렇게 출력: json 형태! 
	}
	
	@RequestMapping(value = "at3", method = RequestMethod.GET)
	public void at3() {
		
	}
	
	//2. 배열 보냄
	@ResponseBody
	@RequestMapping(value = "at2", method = RequestMethod.POST)
	public String[] at2(String[] arr) {
		for(String x : arr) {  			
			System.out.println(x);
		}
		return arr;
	}
	
	@RequestMapping(value = "/at2", method = RequestMethod.GET)
	public void at2() {
		
	}
	
	//1. String 보냄
	@ResponseBody //ajax 통신 하는 애다! jsp로 안가게 막아줌! 
	@RequestMapping(value="/at1", method = RequestMethod.POST)
	public String at1(String msg) {
		return msg + "!!!";
	}
	
	//바로 jsp로 간다! 
	@RequestMapping(value ="/at1", method = RequestMethod.GET)  // / 붙여도 되고 안붙여도 되고
	public void at1() {
		
	}
	
	

	
	
}
