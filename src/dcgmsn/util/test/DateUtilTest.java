package dcgmsn.util.test;

import dcgmsn.util.DateUtil;
import junit.framework.TestCase;

public class DateUtilTest extends TestCase {

	public void testConvertDate() {
		
	}

	public void testToDigitInt() {
		assertEquals(11,DateUtil.toDigitInt("一一"));
		assertEquals(22,DateUtil.toDigitInt("二十二"));
	}

}
