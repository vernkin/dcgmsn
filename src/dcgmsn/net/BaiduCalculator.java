package dcgmsn.net;

import java.net.URLEncoder;

import dcgmsn.util.StringUtil;

public class BaiduCalculator implements InternetResource{

	private final static String findUrl = 
		"http://www.baidu.com/s?cl=3&wd=";
	
	private final static String HEAD = 
		"<img src=\"http://img.baidu.com/img/jsq.gif\" border=\"0\">&nbsp;<font class=f16><b>";
	private final static String END = "</b></font>";
	
	public final static String ENCODING = "gb2312";
	
	public String getAnswer(String... params) throws Exception {
		String keyword = URLEncoder.encode(params[0],ENCODING);
		String data = UrlResource.getUrlData(findUrl+keyword, ENCODING);
		return  StringUtil.substring(data, HEAD, END);
	}

}
