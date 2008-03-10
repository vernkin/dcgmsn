package dcgmsn.service;

import java.util.List;

import dcgmsn.orm.Programme;
import dcgmsn.orm.ProgrammeDAO;
import dcgmsn.orm.Task;
import dcgmsn.orm.TaskDAO;

public class TaskService {

	private ProgrammeDAO progDao;
	private TaskDAO taskDao;
	
	public void save(Programme prog){
		progDao.save(prog);
	}
	
	public void save(Task task){
		taskDao.save(task);
	}
	
	public void delete(Programme prog){
		progDao.delete(prog);
	}
	
	public void delete(Task task){
		taskDao.delete(task);
	}
	
	public List<Programme> getProgrammes(){
		return progDao.getAllProgrammes();
	}

	public void setProgDao(ProgrammeDAO propDao) {
		this.progDao = propDao;
	}

	public ProgrammeDAO getProgDao() {
		return progDao;
	}

	public void setTaskDao(TaskDAO taskDao) {
		this.taskDao = taskDao;
	}

	public TaskDAO getTaskDao() {
		return taskDao;
	}
}
