package dcgmsn.service;

import java.sql.Date;

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
		User ret = userDao.findByAccount(account);
		if(ret!=null){
			ret.setLastLoginDate(new Date(System.currentTimeMillis()));
			userDao.save(ret);
		}
		return ret;
	}
	
	
	public void save(User user){
		userDao.save(user);
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
		User nu = new User(account);
		nu.setRegistDate(new Date(System.currentTimeMillis()));
		userDao.save(nu);
		return getUser(account);
	}
	
}
