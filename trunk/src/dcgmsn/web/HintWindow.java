package dcgmsn.web;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zul.Window;

public class HintWindow{
	
	public static void show(Window parent,String message){
		Map<String,String> args = new HashMap<String,String>();
		args.put("message", message);
		parent.getDesktop().getExecution().createComponents("/hint.zul", parent, args);
	}
}
