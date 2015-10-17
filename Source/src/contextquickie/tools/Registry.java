package contextquickie.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Registry {
	public static String ReadKey(String location, String key) {
		String value = null;
		Process p = null;
		try {
			p = Runtime.getRuntime().exec("reg query " + '"'+ location + "\" /v " + key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				if (line.trim().startsWith(key)) {
					String[] entities = line.trim().split(" +", -1);
					value = entities[2];
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
    	  return value;
	}
	
	public static void WriteKey(String location, String key, String value) {
		try {
			Runtime.getRuntime().exec("reg add " + '"'+ location + "\" /v" + " " + key + " /d " + value + " /f");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
