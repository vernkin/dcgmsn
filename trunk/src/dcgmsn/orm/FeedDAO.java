package dcgmsn.orm;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FeedDAO extends HibernateDaoSupport{
	private static final Logger log = Logger.getLogger(FeedDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(Feed feed) {
		log.debug("saving Feed instance");
		try {
			getHibernateTemplate().saveOrUpdate(feed);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Feed feed) {
		log.debug("deleting Feed instance");
		try {
			getHibernateTemplate().delete(feed);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Feed findById(java.lang.Long id) {
		try {
			Feed instance = (Feed) getHibernateTemplate().get(
					"dcgmsn.orm.Feed", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(Feed instance) {
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
			String queryString = "from Feed as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}
