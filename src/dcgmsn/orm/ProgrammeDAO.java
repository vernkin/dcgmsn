package dcgmsn.orm;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ProgrammeDAO extends HibernateDaoSupport{
	private static final Logger log = Logger.getLogger(ProgrammeDAO.class);

	protected void initDao() {
		// do nothing
	}

	@SuppressWarnings("unchecked")
	public List<Programme> getAllProgrammes(){
		return (List<Programme>)getHibernateTemplate().find("from Programme");
	}
	
	
	public void save(Programme programe) {
		log.debug("saving Programe instance");
		try {
			getHibernateTemplate().saveOrUpdate(programe);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Programme programe) {
		log.debug("deleting Programe instance");
		try {
			getHibernateTemplate().delete(programe);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Programme findById(java.lang.Long id) {
		try {
			Programme instance = (Programme) getHibernateTemplate().get(
					"dcgmsn.orm.Programe", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(Programme instance) {
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
			String queryString = "from Programe as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}
