package dcgmsn.service;

import java.util.List;

import dcgmsn.orm.LabelType;
import dcgmsn.orm.LabelTypeDAO;
import dcgmsn.orm.User;

public class TypeService {

	private LabelTypeDAO typeDao;
	
	@SuppressWarnings("unchecked")
	public List getAllTypes(User user){
		return typeDao.findByUser(user);
	}

	public void setTypeDao(LabelTypeDAO typeDao) {
		this.typeDao = typeDao;
	}

	public LabelTypeDAO getTypeDao() {
		return typeDao;
	}
	
	public void save(LabelType type){
		typeDao.save(type);
	}
	
	public void delete(LabelType type){
		typeDao.delete(type);
	}
}
