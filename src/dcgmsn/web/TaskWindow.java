package dcgmsn.web;

import java.util.List;

import dcgmsn.orm.Task;
import dcgmsn.orm.User;

public class TaskWindow extends BaseWindow{

	private static final long serialVersionUID = -5593957119780123834L;


	public List<Task> getTasks(){
		User user = (User)getDesktop().getSession().getAttribute("user");
		return null;
	}
	
	public void showAddTaskForm(){
		
	}
	
	
	public void showTaskDetails(Task task){
		
	}
}
