package dcgmsn.net.test;

import dcgmsn.net.BaiduKnow;
import junit.framework.TestCase;

public class BaiduKnowTest extends TestCase {

	public void testGetAnswer() throws Exception{
		BaiduKnow bk = new BaiduKnow();
		System.out.println(bk.getAnswer("什么是百度"));
	}

}
