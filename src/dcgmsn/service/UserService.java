package dcgmsn.service;

import java.util.List;

import dcgmsn.orm.DCEvent;
import dcgmsn.orm.DCEventDAO;
import dcgmsn.orm.User;
import dcgmsn.orm.UserDAO;

public class UserService {

	private UserDAO userDao;
	
	private DCEventDAO dceventDao;

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public UserDAO getUserDao() {
		return userDao;
	}
	
	
	public void setDceventDao(DCEventDAO dceventDao) {
		this.dceventDao = dceventDao;
	}

	public DCEventDAO getDceventDao() {
		return dceventDao;
	}

	public User getUser(String account){
		return userDao.findByAccount(account);
	}
	
	
	/**
	 * If the User not exists, auto create a new one
	 * @param account
	 * @return
	 */
	public User sureGetUser(String account){
		User ret = getUser(account);
		if(ret != null)
			return ret;
		userDao.save(new User(account));
		return getUser(account);
	}
	
}
