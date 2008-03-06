package dcgmsn.orm;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(UserDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(User user) {
		log.debug("saving User instance");
		try {
			getHibernateTemplate().saveOrUpdate(user);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(User user) {
		log.debug("deleting User instance");
		try {
			getHibernateTemplate().delete(user);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Long id) {
		try {
			User instance = (User) getHibernateTemplate().get(
					"dcgmsn.orm.User", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(User instance) {
		try {
			List results = getHibernateTemplate().findByExample(instance);
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	

	@SuppressWarnings("unchecked")
	public List findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public User findByAccount(String account){
		try {
			String queryString = "from User as model where model.account= ? ";
			List list =  getHibernateTemplate().find(queryString,account);
			if(list == null || list.size() == 0)
				return null;
			return (User)list.get(0);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public static UserDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (UserDAO) ctx.getBean("UserDAO");
	}
	
	
}
