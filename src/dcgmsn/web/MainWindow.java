package dcgmsn.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;

import dcgmsn.orm.User;
import dcgmsn.robot.MyServer;
import dcgmsn.service.UserService;

public class MainWindow extends BaseWindow {
	private final static Logger logger = Logger.getLogger(MainWindow.class); 
	private static final long serialVersionUID = 5972840569820907001L;

	private UserService userService;
	static{
		
	}
	
	public MainWindow(){
		userService = (UserService)DCBeanFactory.getBean("UserService");
		
		
	}
	
	public void loadPage(String name) {
		//FIXME for test user
		User user = userService.sureGetUser("test@test.com");
		getDesktop().getSession().setAttribute("user", user);
		
		removeContentBox();
		Component hb = getComponent("/mainWnd/contentBox");
		name = name.replace('_', '/');
		Component comp = createComponents(name + ".zul", hb, null);
		hb.appendChild(comp);
	}
	
	public void removeContentBox() {
		Component hb = getComponent("/mainWnd/contentBox");
		List children = hb.getChildren();
		int size = children.size();
		for (int i = size - 1; i >= 0; i--) {
			hb.removeChild((Component) children.get(i));
		}
	}
}
