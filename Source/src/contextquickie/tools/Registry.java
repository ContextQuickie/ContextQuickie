package contextquickie.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registry {

	public static String ReadKey(String location, String key) {

		String value = null;
		Process p = null;
		try {
			p = Runtime.getRuntime().exec("reg query " + '"' + location + "\" /v " + key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		/**
		 * Regular expression for parsing the output of the query
		 * " +": One or more spaces
		 * "[A-Z_]+": one of  REG_SZ, REG_MULTI_SZ, REG_EXPAND_SZ, REG_DWORD, REG_QWORD, REG_BINARY, REG_NONE
		 * "(.*)": The queried value
		 */
		Pattern queryPattern = Pattern.compile(" +" + key + " +" + "[A-Z_]+" + " +" + "(.*)" + "$");
		try {
			while ((line = reader.readLine()) != null) {
				Matcher matcher = queryPattern.matcher(line);
				if (matcher.matches()) {
					value = matcher.group(1);
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
			Runtime.getRuntime().exec("reg add " + '"' + location + "\" /v" + " " + key + " /d " + value + " /f");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
