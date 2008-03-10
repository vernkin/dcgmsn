package dcgmsn.net.test;

import dcgmsn.net.BaiduNews;
import junit.framework.TestCase;

public class BaiduNewsTest extends TestCase {

	public void testGetAnswer() throws Exception{
		BaiduNews bn = new BaiduNews();
		System.out.println(bn.getAnswer("新闻"));
	}

}
