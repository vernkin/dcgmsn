package dcgmsn.orm;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CommonDAO extends HibernateDaoSupport{
	private static final Logger log = Logger.getLogger(CommonDAO.class);

	protected void initDao() {
		// do nothing
	}
	
	public int executeUpdate(String hql){
		log.debug(hql);
		try {
			int ret = getHibernateTemplate().bulkUpdate(hql);
			log.debug(hql+" successful");
			return ret;
		} catch (RuntimeException re) {
			log.error(hql+" failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List executeQuery(String hql){
		log.debug(hql);
		try {
			List list = getHibernateTemplate().find(hql);
			log.debug(hql+" successful");
			return list;
		} catch (RuntimeException re) {
			log.error(hql+" failed", re);
			throw re;
		}
	}
}
