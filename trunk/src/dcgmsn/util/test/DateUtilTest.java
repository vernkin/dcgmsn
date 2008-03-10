package dcgmsn.util.test;

import dcgmsn.util.DateUtil;
import junit.framework.TestCase;

public class DateUtilTest extends TestCase {

	public void testConvertDate() throws Exception{
		Object[] ret = DateUtil.convertDate("2005年三月四日七点三十分吃饭");
		printArray(ret);
		
		ret = DateUtil.convertDate("23点40分到八号九点happy");
		printArray(ret);
	}

	private void printArray(Object[] in){
		for(int i=0;i<in.length;i++)
			System.out.println(i+":"+in[i]);
	}
	
	/*
	public void testToDigitInt() {
		assertEquals(4,DateUtil.toDigitInt("四"));
		assertEquals(11,DateUtil.toDigitInt("一一"));
		assertEquals(22,DateUtil.toDigitInt("二十二"));
		assertEquals(10020,DateUtil.toDigitInt("一万二十"));
		assertEquals(12300,DateUtil.toDigitInt("一万二三"));
		assertEquals(10410,DateUtil.toDigitInt("一万零四百一"));
		assertEquals(10401,DateUtil.toDigitInt("一万零四百零一"));
		assertEquals(10041,DateUtil.toDigitInt("一万零四十一"));
		assertEquals(10079,DateUtil.toDigitInt("一万七十九"));
	}
	*/
}
