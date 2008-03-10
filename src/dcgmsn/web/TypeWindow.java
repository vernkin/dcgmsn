package dcgmsn.web;

import java.util.List;

import org.zkoss.zul.ListModelList;

import dcgmsn.msc.ColorUnit;
import dcgmsn.orm.LabelType;
import dcgmsn.orm.User;
import dcgmsn.service.TypeService;

public class TypeWindow extends BaseWindow{

	private static final long serialVersionUID = -7179354023706952287L;
	
	private TypeService typeService;
	
	private ListModelList model = null;
	public TypeWindow(){
		typeService = (TypeService)DCBeanFactory.getBean("TypeService");
	}
	
	@SuppressWarnings("unchecked")
	public List getColors(){
		return ColorUnit.colors;
	}
	
	@SuppressWarnings("unchecked")
	public ListModelList getTypes(){
		if(model == null){
			model = new ListModelList();
			User user = (User)getDesktop().getSession().getAttribute("user");
			model.addAll(typeService.getAllTypes(user));
		}
		return model;
	}

	public void add(LabelType type){
		typeService.save(type);
		model.add(type);
	}
	
	public void modify(LabelType type){
		typeService.save(type);
		int index = model.indexOf(type);
		model.set(index, type);
	}
	
	public void delete(LabelType type){
		typeService.delete(type);
		model.remove(type);
	}
}
