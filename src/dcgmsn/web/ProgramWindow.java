package dcgmsn.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.ListModelList;

import dcgmsn.orm.Programme;
import dcgmsn.service.TaskService;

public class ProgramWindow extends BaseWindow {

	private static final long serialVersionUID = 641420359419288927L;

	private TaskService taskService;
	private ListModelList model;
	
	public ProgramWindow(){
		taskService = (TaskService)DCBeanFactory.getBean("TaskService");
	}
	
	
	public ListModelList getProgrammes(){
		if(model==null){
			model = new ListModelList();
			model.addAll(taskService.getProgrammes());
		}
		return model;
	}
	
	public void showAddProgForm(){
		removeLast();
		Component hb = getComponent("/mainWnd/progWnd/hb");
		createComponents("/task/addProgramme.zul",hb,null);
	}
	
	
	@SuppressWarnings("unchecked")
	public void showProgDetails(Programme prog){
		removeLast();
		Component hb = getComponent("/mainWnd/progWnd/hb");
		Map args = new HashMap();
		args.put("programme", prog);
		createComponents("/task/showProgramme.zul",hb,args);
	}
	
	
	public void add(String name,String path){
		Programme prog = new Programme();
		prog.setName(name);
		prog.setPath(path);
		taskService.save(prog);
		model.add(prog);
		showProgDetails(prog);
	}
	
	public void modify(Programme prog){
		taskService.save(prog);
		int index = model.indexOf(prog);
		model.set(index, prog);
	}
	
	public void delete(Programme prog){
		taskService.delete(prog);
		model.remove(prog);
		if(model.size()!=0){
			showProgDetails((Programme)model.getElementAt(0));
		}else {
			showAddProgForm();
		}
	}
	
	public boolean isValidPath(String path){
		File f =new File(path);
		return f.exists() && f.isFile();
	}
	
	@SuppressWarnings("unchecked")
	private void removeLast(){
		Component hb = getComponent("/mainWnd/progWnd/hb");
		List children = hb.getChildren();
		int size = children.size();
		if(size>=2){
			hb.removeChild((Component)children.get(size-1));
		}
	}
}
