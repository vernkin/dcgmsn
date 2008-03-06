package dcgmsn.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class UrlResource {

	public static String getUrlData(String urlStr, String encoding) 
		throws Exception{
		return getUrlData(urlStr, encoding, null);
	}

	public static String getUrlData(String urlStr, String encoding,
			String postParam) throws Exception{
		if(encoding == null)
			encoding = "gb2312";
		StringBuffer document = new StringBuffer();

		URL url = new URL(urlStr);
		URLConnection conn = url.openConnection();
		if (postParam != null) {
			conn.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(conn
					.getOutputStream(), encoding);
			out.write(postParam);
			out.flush();
			out.close();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream(),encoding));
		String line = null;
		while ((line = reader.readLine()) != null) {
			document.append(line + "\n");
		}
		reader.close();

		return document.toString();
	}

	public static void main(String[] args) throws Exception{
		System.out.println(getUrlData("http://www.learnjava.cn/Article/j2se/socket/200705/177.html", null));
	}
}
