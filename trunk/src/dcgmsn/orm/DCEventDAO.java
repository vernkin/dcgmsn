package dcgmsn.orm;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DCEventDAO extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(DCEventDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(DCEvent evt) {
		log.debug("saving DCEvent instance");
		try {
			getHibernateTemplate().saveOrUpdate(evt);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DCEvent evt) {
		log.debug("deleting DCEvent instance");
		try {
			getHibernateTemplate().delete(evt);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Long id) {
		try {
			User instance = (User) getHibernateTemplate().get(
					"dcgmsn.orm.DCEvent", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(DCEvent instance) {
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
			String queryString = "from DCEvent as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<DCEvent> findByUser(User user){
		try {
			String queryString = "from DCEvent as e where e.user.id = ?";
			return getHibernateTemplate().find(queryString, user.getId());
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}
