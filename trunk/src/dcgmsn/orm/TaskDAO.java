package dcgmsn.orm;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TaskDAO extends HibernateDaoSupport{
	private static final Logger log = Logger.getLogger(TaskDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(Task task) {
		log.debug("saving Task instance");
		try {
			getHibernateTemplate().saveOrUpdate(task);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Task task) {
		log.debug("deleting Task instance");
		try {
			getHibernateTemplate().delete(task);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Task findById(java.lang.Long id) {
		try {
			Task instance = (Task) getHibernateTemplate().get(
					"dcgmsn.orm.Task", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(Task instance) {
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
			String queryString = "from Task as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Task> findByUser(User user){
		try {
			String queryString = "from Task as t where t.owner.id = ?";
			return getHibernateTemplate().find(queryString, user.getId());
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}
