package dcgmsn.net;

import java.net.URLEncoder;

import org.apache.log4j.Logger;

import dcgmsn.util.StringUtil;

public class BaiduKnow implements InternetResource {
	public final static Logger logger = Logger.getLogger(BaiduKnow.class);
	
	private final static String findUrl = 
		"http://zhidao.baidu.com/q?ct=17&pn=0&tn=ikaslist&rn=10&word=";
	//end with "\""
	private final static String FirstSearchResultHead = "<table border=0 cellpadding=0 cellspacing=0><tr><td class=f><a href=\"";
	private final static String FirstSearchResultEnd = "\"";
	
	private final static String AnswerUrl = "http://zhidao.baidu.com";
	
	//end with "</ca>"
	private final static String BestAnsHead = "<div class=\"f14 p90 pl10\"> <ca>"; 
	private final static String BestAnsEnd = "</ca>";
	
	//end with </div>
	private final static String OtherAnsHead = "<div class=\"f14 p90 pl10\">";
	private final static String OtherAnsEnd = "</div>";
	
	// end with <br><span class=\"wikifs\">
	private final static String BaikeHead = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"wikif\">";
	private final static String BaikeEnd = "<br><span class=\"wikifs\">";
	
	private final static String BaikeURLHead = "<font size=\"3\"><a href=\"";
	private final static String BaikeURLEnd = "\" target=\"_blank\">";
		
	public final static String ENCODING = "gb2312";
	
	public String getAnswer(String... params) throws Exception {
		String keyword = URLEncoder.encode(params[0],ENCODING);
		String data = UrlResource.getUrlData(findUrl+keyword, ENCODING);
		
		//try to find baike
		String ret = StringUtil.substring(data, BaikeHead, BaikeEnd);
		if(ret != null)
			return getBaikeAns(ret);
		
		ret = StringUtil.substring(data, FirstSearchResultHead, FirstSearchResultEnd);
		if(ret == null)
			return null;
		
		//goto answer page
		data = UrlResource.getUrlData(AnswerUrl+ret, ENCODING);
		
		//get the best answers
		ret = StringUtil.substring(data, BestAnsHead, BestAnsEnd);		
		if(ret != null)
			return StringUtil.skipHtml(ret);
		
		//try to other answers
		return  StringUtil.skipHtml(StringUtil.substring(data, OtherAnsHead, OtherAnsEnd));
	}

	
	private static String getBaikeAns(String data){
		int startIndex = data.indexOf(BaikeURLHead);
		int endIndex = data.indexOf(BaikeURLEnd,
				startIndex+BaikeURLHead.length());

		String url =  data.substring(startIndex+BaikeURLHead.length(), endIndex);
		String desp = data.substring(endIndex+BaikeURLEnd.length());
		
		return StringUtil.skipHtml(desp) + "更多内容见 "+url;
	}
}
