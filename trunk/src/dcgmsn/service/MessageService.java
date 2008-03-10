package dcgmsn.service;

import java.util.List;

import dcgmsn.orm.Message;
import dcgmsn.orm.MessageDAO;
import dcgmsn.orm.User;

public class MessageService {

	private MessageDAO msgDao;
	
	public void save(Message msg){
		msgDao.save(msg);
	}
	
	public void delete(Message msg){
		msgDao.delete(msg);
	}
	
	public List<Message> getMsgByFrom(User user){
		return msgDao.findByFrom(user);
	}
	
	public List<Message> getMsgByTo(User user){
		return msgDao.findByTo(user);
	}

	public void setMsgDao(MessageDAO msgDao) {
		this.msgDao = msgDao;
	}

	public MessageDAO getMsgDao() {
		return msgDao;
	}
}
