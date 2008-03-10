package dcgmsn.util;

import dcgmsn.service.UserService;
import dcgmsn.web.DCBeanFactory;

public class ServiceCenter {

	public static final UserService userService = (UserService)DCBeanFactory.getBean("UserService");
}
