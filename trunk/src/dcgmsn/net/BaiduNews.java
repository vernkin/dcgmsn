package dcgmsn.net;

import java.net.URLEncoder;
import java.util.List;

import dcgmsn.util.StringUtil;

public class BaiduNews implements InternetResource {
	public final static String ENCODING = BaiduKnow.ENCODING;
	
	private final static String findUrl = "http://news.baidu.com/ns?tn=news&from=news&cl=2&rn=20&ct=0&word=";
	
	//private final static String NewsHead = "<td class=\"zh\" width=100%><a href=\"";
	private final static String NewsHead = "<td class=\"zh\" width=100%>";
	private final static String NewsEnd = "\n";
	
	public String getAnswer(String... params) throws Exception {
		String keyword = URLEncoder.encode(params[0],ENCODING);
		String data = UrlResource.getUrlData(findUrl+keyword, ENCODING);
		List<String> news = StringUtil.getManySubstring(data, NewsHead, NewsEnd, 4);
		
		StringBuffer ret = new StringBuffer(1024);
		for(String s : news)
			ret.append(StringUtil.skipHtml(s)+"\n");
			
		return ret.toString();
	}

}
