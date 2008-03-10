package dcgmsn.web;

import org.apache.log4j.Logger;

import dcgmsn.orm.User;
import dcgmsn.service.UserService;

public class UserWindow extends BaseWindow {
	private Logger logger = Logger.getLogger(UserWindow.class);
	
	private static final long serialVersionUID = -3985293080077837291L;

	private UserService userService;
	
	public UserWindow(){
		userService = (UserService)DCBeanFactory.getBean("UserService");
	}
	
	
	public void modify(User user){
		try{
			userService.save(user);
			HintWindow.show(this, "修改用户信息成功");
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error(ex.getMessage(), ex);
			HintWindow.show(this, "修改用户信息失败");
		}
	}
}
