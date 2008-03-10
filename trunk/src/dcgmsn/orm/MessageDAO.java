package dcgmsn.orm;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class MessageDAO extends HibernateDaoSupport{
	private static final Logger log = Logger.getLogger(MessageDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(Message msg) {
		log.debug("saving Message instance");
		try {
			getHibernateTemplate().saveOrUpdate(msg);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Message msg) {
		log.debug("deleting Message instance");
		try {
			getHibernateTemplate().delete(msg);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Message findById(java.lang.Long id) {
		try {
			Message instance = (Message) getHibernateTemplate().get(
					"dcgmsn.orm.Message", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(Message instance) {
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
			String queryString = "from Message as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> findByFrom(User user){
		try {
			String queryString = "from Message as t where t.from.id = ? order by createDate desc";
			return getHibernateTemplate().find(queryString, user.getId());
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> findByTo(User user){
		try {
			String queryString = "from Message as t where t.to.id = ? order by createDate desc";
			return getHibernateTemplate().find(queryString, user.getId());
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}
