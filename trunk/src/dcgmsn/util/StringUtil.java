package dcgmsn.util;

import java.util.LinkedList;
import java.util.List;

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
	
	
	/**
	 * Return at most maxCount substring between begin and end string
	 * @param data the source string
	 * @param begin the begin string of target string
	 * @param end the end string of target string
	 * @param maxCount at most value to return
	 * @return if null, not result found
	 */
	public static List<String> getManySubstring(String data,String begin,
			String end,int maxCount){
		int startIndex = data.indexOf(begin);
		if(startIndex < 0)
			return null;
		int endIndex = data.indexOf(end,startIndex+begin.length());
		if(endIndex < 0)
			return null;
		List<String> ret = new LinkedList<String>();
		ret.add(data.substring(startIndex+begin.length(), endIndex));
		int count = 1;
		while(count < maxCount){
			startIndex = data.indexOf(begin,endIndex+end.length());
			if(startIndex < 0)
				break;
			endIndex = data.indexOf(end,startIndex+begin.length());
			if(endIndex < 0)
				break;
			ret.add(data.substring(startIndex+begin.length(), endIndex));
			count++;
		}		
		return ret;
	}
	
	
	public static String skipHtml(String in){
		in = in.replaceAll("&nbsp;", " ");
		StringBuffer bu = new StringBuffer(1024);
		boolean start = false;
		
		for(int i=0;i<in.length();i++){
			char c = in.charAt(i);
			if(start){
				if(c == '>')
					start = false;
			}else{
				if(c == '<')
					start = true;
				else
					bu.append(c);
			}
		}
		
		return bu.toString();
	}
}
