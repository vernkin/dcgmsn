package dcgmsn.net;

import com.google.gdata.client.codesearch.CodeSearchService;

import com.google.gdata.util.common.xml.XmlWriter;
import com.google.gdata.data.codesearch.CodeSearchFeed;
import com.google.gdata.data.codesearch.CodeSearchEntry;
import com.google.gdata.data.codesearch.Match;

import dcgmsn.util.SimpleCommandLineParser;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;

/**
 * An application that serves as a sample to show how the CodeSearchService can
 * be used to retrieve data from Google Code Search.
 * 
 * 
 */

public class CodeSearch implements InternetResource {
	public static final String ENCODING = "utf-8";
	
	private static final String CODESEARCH_FEEDS_URL = "http://www.google.com/codesearch/feeds/search?";

	private static CodeSearchService codesearchService = 
		new CodeSearchService("gdata-sample-codesearch");

	public static void run(PrintStream out,String query, String nresults, 
			String start) throws Exception {
		URL privateFeedUrl = new URL(CODESEARCH_FEEDS_URL + "q=" + query
				+ "&start-index=" + start + "&max-results=" + nresults);
		retrieveFeed(out,privateFeedUrl);
	}

	private static void retrieveFeed(PrintStream out,URL privateFeedUrl) 
		throws Exception {
		PrintWriter writer = new PrintWriter(out);
		XmlWriter xmlWriter = new XmlWriter(writer);
		CodeSearchFeed myFeed = codesearchService.getFeed(privateFeedUrl,
				CodeSearchFeed.class);
		out.println("匹配记录数: " + myFeed.getEntries().size());
		int i = 0;
		int count = 0;
		for (CodeSearchEntry entry : myFeed.getEntries()) {
			count++;
			if(count > 3)
				break;
			
			out.println("#"+(++i)+" 详细参考: " + entry.getHtmlLink().getHref()); 
			entry.getFile().generate(xmlWriter,
					codesearchService.getExtensionProfile());
			out.println(" 匹配行: ");
			for (Match m : entry.getMatches()) {
				out.println(m.getLineNumber() + ": "
						+ m.getLineText().getPlainText());
				m.generate(xmlWriter, codesearchService.getExtensionProfile());
			}
		}
	}

	/**
	 * Main entry point. Parses arguments and creates and invokes the
	 * CodeSearchClient.
	 */
	public static void main(String[] arg) throws Exception {
		String query = "System.out";
		OutputStream bos = new ByteArrayOutputStream(1024);
		PrintStream ps = new PrintStream(bos);
		CodeSearch.run(ps,query,null,null);
		System.out.println(bos.toString());
	}


	public String getAnswer(String... params) throws Exception {
		String query = params[0];
		OutputStream bos = new ByteArrayOutputStream(1024);
		PrintStream ps = new PrintStream(bos);
		CodeSearch.run(ps,query,null,null);
		return bos.toString();
	}
}
