package kr.co.ca;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController  //ajax 전문 controller 4버전부터 생김 
public class RestTestController {
	
	
	@RequestMapping(value="rt4", method = RequestMethod.POST)
	public String rt4(@RequestBody Map<String, Object> map) {
		//Object obj = map.get("liststr"); -> System.out.println(obj); 형태찍어보기 ->가장 겉에있는것, 그 안의 것! 
		List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("liststr");
		for(Map<String, Object> m : list) {
			System.out.println(":::::::::::");
			System.out.println(m.get("id"));
			System.out.println(m.get("name"));
			System.out.println(m.get("age"));
			System.out.println(":::::::::::");
		}
		return "hello";
	}
	
	@RequestMapping(value="rt3", method = RequestMethod.POST)
	public String rt3(@RequestBody Map<String, Object> map) {
		//Object obj 로 받았을때 나오는 형태가 list 안에 map  형태여서, List<Map>형태로 받음 
		//Object obj = map.get("test4");
		
		List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("test4");
		// 윗줄에서 Object obj 로받았을 때! System.out.println(obj);  //대괄호로 감싸져있음. 중괄호.  []->list, {} ->map
		
		for(Map<String, Object> m : list) {  //풀어헤치기 
			System.out.println("::::::::::");
			System.out.println(m.get("id"));
			System.out.println(m.get("name"));
			System.out.println(m.get("age"));
			System.out.println("::::::::::");
		}
		
		return "hello";  //list나 map 반환하는 것 해보세요. view 에 찍히게(success쪽엥서 작업해보기) 
	}
	
	@RequestMapping(value="rt2", method = RequestMethod.POST)
	public Map<String, Object> rt2(@RequestBody Map<String, Object> map) {
		System.out.println(map.get("test1"));
		System.out.println(map.get("test2"));
		
		return map;
	}
	
	@RequestMapping(value="rt1", method=RequestMethod.POST)
	public String rt1(@RequestBody String test1) {
		System.out.println(test1);
		return "ok";  //콘솔창에 뜸~ 
	}
}
