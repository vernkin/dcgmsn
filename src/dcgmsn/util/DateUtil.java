package dcgmsn.util;

import java.util.Date;

public class DateUtil {

	public static final String ZH_NUM = "零一二三四五六七八九";
	

	
	/**
	 * convert the String date,like 2005年四月八日
	 * to java.util.Date
	 * @param in
	 * @return
	 */
	public static Date convertDate(String in){
		return null;
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
		int base = 0;
		boolean unit = true;
		for(int i=0;i<in.length();i++){
			if((index=ZH_NUM.indexOf(in.charAt(i)))>=0){
				if(!unit)
					ret = ret * 10 + index;
				else{
					if(index == 0)
						base = 1;
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
				ret *= base;
				unit = true;
			}
			
		}
		//FIXME not finished yet
		return ret;
	}
	
	
	public static void main(String[] args){
		
	}
}
