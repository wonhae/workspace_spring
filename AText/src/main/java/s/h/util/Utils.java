package s.h.util;

import java.util.UUID;

public class Utils {
		
	
	public static String makeNewName(String orgname) {
		UUID uid = UUID.randomUUID();
		String newName = uid.toString()+"_"+orgname;
		return newName;
	}
}
