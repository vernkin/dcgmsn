package dcgmsn.robot.util;

public class MsgTypeUtil {
	
	public static final String CAL_STR = " ()0123456789+-*/!^";
	
	public static boolean isCalculateStr(String msg){
		for(int i=0;i<msg.length();i++){
			if(CAL_STR.indexOf(msg.charAt(i)) < 0)
				return false;
		}
		return true;
	}
	
	public static boolean isCodeStr(String msg){
		for(int i=0;i<msg.length();i++){
			int c = msg.charAt(i);
			if(c<0 || c>256)
				return false;
		}
		return true;
	}
	
}
