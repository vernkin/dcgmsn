package dcgmsn.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DateUtil {

	public static final String ZH_NUM = "零一二三四五六七八九";
	public static final String EN_NUM = "0123456789";
	
	public static final String ALL_NUM = "0123456789零一二三四五六七八九十";

	public static final String OFFSET_PRE = "前昨今明后";
	public static final String OFFSET_PRE_1 = "上今下";
	
	public static final String IGNORE_STR = "个";
	
	public static String DateReg = null;
	public static Pattern DatePat = null;
	
	public static final String[] DayUnit = 
		{"年","月","日","天","号","时","小时","点","分","周"};
	public static final int[] DayBit = 
		{Calendar.YEAR,Calendar.MONTH,Calendar.DATE,Calendar.DATE,Calendar.DATE,
		Calendar.HOUR_OF_DAY,Calendar.HOUR_OF_DAY,Calendar.HOUR_OF_DAY,
		Calendar.MINUTE,Calendar.WEEK_OF_YEAR};
	
	private static final Map<String,Integer> UnitBitMap = 
		new HashMap<String,Integer>();
	
	static{
		String digitReg = "(["+"0123456789零一二三四五六七八九十]+)";
		String[] units = {"年","月","日|天|号","小时|时|点","分"};
		StringBuffer buf = new StringBuffer();
		for(String u : units){
			buf.append("("+digitReg+"("+u+")){0,1}");
		}
		DateReg = buf.toString()+"(.*)";
		
		DatePat = Pattern.compile(DateReg);
		
		for(int i=0;i<DayUnit.length;i++){
			UnitBitMap.put(DayUnit[i], DayBit[i]);
		}
	}
	/**
	 * convert the String date,like 2005年四月八日
	 * to java.util.Date
	 * @param in
	 * @return if date1 to date2 XXXX, date[0] and date[1] is java.util.Date, date[2] is
	 * the remaining String. if in date XXXX,only the date[0] is java.util.Date
	 */
	public static Object[] convertDate(String in) throws Exception{
		Matcher m = DatePat.matcher(in);
		if(m.matches()){
			Calendar cal1 = Calendar.getInstance(); 
			for(int i=2;i<m.groupCount();i+=3){
				if(m.group(i) == null)
					continue;
				cal1.set(UnitBitMap.get(m.group(i+1)), 
						toDigitInt(m.group(i)));
			}
			String desp = m.group(m.groupCount());
			char c = desp.charAt(0);
			if(c == '至' || c == '到'){
				Object[] ret2 = convertDate(desp.substring(1));
				Object[] ret = new Object[ret2.length+1];
				ret[0] = cal1.getTime();
				System.arraycopy(ret2, 0, ret, 1, ret2.length);
				return ret;
			}else{
				return new Object[]{cal1.getTime(),desp};
			}
		}else{
			return new String[]{in};
		}
	}
	
	/**
	 * The max Unit is "亿"
	 * @param in
	 * @return
	 */
	public static int toDigitInt(String in) throws NumberFormatException{
		try{
			int ret = Integer.parseInt(in);
			return ret;
		}catch(NumberFormatException ex){
			//;try to analysis in chinese mode
		}
		int ret = 0;
		int index = 0;
		int base = 10;
		int prebase = 10;
		int preValue = 0;
		boolean unit = true;
		boolean occurUnit = false; //occur unit before
		for(int i=0;i<in.length();i++){
			index = ZH_NUM.indexOf(in.charAt(i));
			//may be the Arabic number
			if(index < 0)
				index = EN_NUM.indexOf(in.charAt(i));
			if(index>=0){
				if(unit){
					preValue = index; //一万一.. 一万零..
					base = base / 10;
				}else{
					if(occurUnit){
						ret += preValue * base; //一万三五百 （3500）
						prebase = base;
						base = base / 10;
						
						if(base <= 0)
							throw new NumberFormatException("Can't eval number "+in);
					}else{
						ret += preValue;
						ret *= 10;
						base = 1;
					}			
					preValue = index;		
				}
				unit = false; 
			}else{
				if(in.charAt(i) == '十')
					base = 10;
				else if(in.charAt(i) == '百')
					base = 100;
				else if(in.charAt(i) == '千')
					base = 1000;
				else if(in.charAt(i) == '万')
					base = 10000;
				else if(in.charAt(i) == '亿')
					base = 100000000;
				else
					throw new NumberFormatException("Can't eval number "+in);
				if(base >= prebase){
					if(unit){
						ret *= base; //万万,千万
					}else{
						ret += preValue; // 一万
						ret *= base;
					}
				}else{
					ret += preValue * base; //一万二十
				}
				prebase = base;
				unit = true;
				occurUnit = true;
			}
			
		}
		if(!unit || !occurUnit)
			ret += preValue * base;
		//FIXME not finished yet
		return ret;
	}
	
	
	public static void main(String[] args){
		
	}
}
