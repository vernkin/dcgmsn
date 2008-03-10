package dcgmsn.net.test;

import dcgmsn.net.BaiduCalculator;
import junit.framework.TestCase;

public class BaiduCalculatorTest extends TestCase {

	public void testGetAnswer() throws Exception{
		BaiduCalculator bc = new BaiduCalculator();
		System.out.println(bc.getAnswer("56+78"));
	}

}
