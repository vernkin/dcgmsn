package dcgmsn.service;

import java.util.List;

import dcgmsn.orm.DCEvent;
import dcgmsn.orm.DCEventDAO;
import dcgmsn.orm.User;

public class TimelineService {
	private DCEventDAO dceventDao;
	
	public void setDceventDao(DCEventDAO dceventDao) {
		this.dceventDao = dceventDao;
	}

	public DCEventDAO getDceventDao() {
		return dceventDao;
	}
	
	public List<DCEvent> getDCEventByUser(User user){
		return dceventDao.findByUser(user);
	}
	
	public void add(DCEvent evt){
		dceventDao.save(evt);
	}
	
	public void removeDCEvent(DCEvent evt){
		dceventDao.delete(evt);
	}
}
