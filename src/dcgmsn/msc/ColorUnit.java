package dcgmsn.msc;

import java.util.ArrayList;
import java.util.List;

public class ColorUnit {

	private String name;
	
	private String style;
	
	public static final List<ColorUnit> colors = buildColors();
		
	public static List<ColorUnit> buildColors(){
		List<ColorUnit> ret = new ArrayList<ColorUnit>();
		String[] values = {"黑色","black","白色","white","红色","red","黄色","yellow","绿色","green","浅绿色","aqua",
				"银色","Silver","橄榄色","Olive","蓝绿色","teal"};
		for(int i=0;i<values.length;i+=2){
			ret.add(new ColorUnit(values[i],values[i+1]));
		}
		return ret;
	}
	
	
	public ColorUnit(String name,String style){
		this.setName(name);
		this.setStyle(style);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getStyle() {
		return style;
	}
	
	
	public String toString(){
		return name+"("+style+")";
	}
}
