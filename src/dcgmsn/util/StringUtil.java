package dcgmsn.util;

public class StringUtil {

	/**
	 * get the substring (first one) between begin and end string
	 * @param data the source string
	 * @param begin the begin string of target string
	 * @param end the end string of target string
	 * @return if null, not found
	 */
	public static String substring(String data,String begin,String end){
		int startIndex = data.indexOf(begin);
		if(startIndex < 0)
			return null;
		int endIndex = data.indexOf(end,startIndex+begin.length());
		if(endIndex < 0)
			return null;	
		return data.substring(startIndex+begin.length(), endIndex);
	}
	
}
