package dcgmsn.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import dcgmsn.orm.User;
import dcgmsn.orm.UserDAO;


public class EmailService {
	private MailSender mailSender;

	private String host = null;

	private String userName = null;

	private UserDAO userDAO = null;

	public void sendSimpleMail(String to, String subject, String content) {
		/*SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(getEmailAddress(userName));
		message.setTo(to);
		message.setSubject(subject);
		content = content + "\n\n   南京大学软件学院图书室\n    "
				+ FreqUtil.getToday()+"\n\n------本邮件由系统自动生成-------\n"+
				getSystemAddress();
		message.setText(content);
		mailSender.send(message);	*/	
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public String getEmailAddress(String name){
		return name + "@" + host;
	}

	public void resetPassword(User user){
		/*String pwd = UserHelper.generatePassword(8);
		String encrp = MD5.compute(pwd);
		String job = user.getJob();
		String content = "尊敬的 "+user.getName()+" "+job+":\n"+
			"   您好!\n"+"   你的新密码为 "+pwd+"\n";
		String title = user.getName()+" "+job+" 在图书室订书系统的新密码";
		user.setPassword(encrp);
		userDAO.save(user);
		sendSimpleMail(getEmailAddress(user.getUserName()),title,content);
		*/
	}
	
	private String getSystemAddress(){
		try {
			String url = "http://"+InetAddress.getLocalHost().getHostAddress()+":8080/libmgr\n";
			return "订书系统网址 "+url;
		} catch (UnknownHostException e) {
			return "抱歉,无法获得网址!";
		}
	}
}
