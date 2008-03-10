package dcgmsn.orm;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class LabelTypeDAO extends HibernateDaoSupport {
	private static final Logger log = Logger.getLogger(FeedDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(LabelType type) {
		log.debug("saving LabelType instance");
		try {
			getHibernateTemplate().saveOrUpdate(type);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LabelType type) {
		log.debug("deleting LabelType instance");
		try {
			getHibernateTemplate().delete(type);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LabelType findById(java.lang.Long id) {
		try {
			LabelType instance = (LabelType) getHibernateTemplate().get(
					"dcgmsn.orm.LabelType", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(LabelType instance) {
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
			String queryString = "from LabelType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List findByUser(User user){
		String query = "from LabelType where user.id = ?";
		return getHibernateTemplate().find(query,user.getId());
	}
}
