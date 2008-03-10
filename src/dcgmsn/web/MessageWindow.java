package dcgmsn.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;

import dcgmsn.orm.Message;
import dcgmsn.orm.User;
import dcgmsn.service.MessageService;
import dcgmsn.service.UserService;

public class MessageWindow extends BaseWindow{

	private static final long serialVersionUID = 1086753724227943024L;

	private UserService userService;
	private MessageService msgService;
	
	public MessageWindow(){
		userService = (UserService)DCBeanFactory.getBean("UserService");
		msgService = (MessageService)DCBeanFactory.getBean("MessageService");
	}
	
	public void delete(Message msg){
		msgService.delete(msg);
	}
	
	@SuppressWarnings("unchecked")
	public void loadBox(boolean isInbox){
		Map arg = new HashMap();
		String path = null;
		User user = (User)getDesktop().getSession().getAttribute("user");
		if(isInbox){
			arg.put("messages",msgService.getMsgByFrom(user));
			path = "/msg/inbox.zul";
		}else{
			arg.put("messages",msgService.getMsgByTo(user));
			path = "/msg/outbox.zul";
		}
		removeData();
		Component hb = getComponent("/mainWnd/msgWnd/data");
		Component comp = createComponents(path, hb, arg);
		hb.appendChild(comp);
	}
	
	@SuppressWarnings("unchecked")
	private void removeData(){
		Component hb = getComponent("/mainWnd/msgWnd/data");
		List children = hb.getChildren();
		int size = children.size();
		if(size>0){
			hb.removeChild((Component)children.get(size-1));
		}
	}
}
