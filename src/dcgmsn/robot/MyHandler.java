package dcgmsn.robot;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import com.incesoft.botplatform.sdk.RobotException;
import com.incesoft.botplatform.sdk.RobotHandler;
import com.incesoft.botplatform.sdk.RobotMessage;
import com.incesoft.botplatform.sdk.RobotServer;
import com.incesoft.botplatform.sdk.RobotSession;
import com.incesoft.botplatform.sdk.RobotUser;

/**
 * @author LiBo
 */
public class MyHandler implements RobotHandler {
	
	
	public final String commandList =   "*****************************************\r" +
										"**  BOTPLATFORM SDK DEMO COMMAND LIST  **\r" + 
										"*****************************************\r" +
									    " preface --- test message preface. \r" +
									    " emo ------- test emoticon.\r" + 
									    " nudge ----- test nudge.\r" + 
									    " p4 -------- test msn activity.\r" +
									    " typing ---- test typing info.\r" +
									    " name ------ test change friendly name.\r" +
									    " pm -------- test change personal msg.\r" +
									    " dp -------- test change display picture.\r" +
									    " push <msn account> ------ test push message.\r" +
									    " call <msn account> ------ test create session.\r" +
									    " invite <msn account> ---- test invite user.\r" +
									    " help ------ print this command list.\r" + 
									    " ? --------- print this command list.\r";
										
	private RobotServer server = null;
	
	public MyHandler(RobotServer s) {
		server = s;
	}
	
    /**
     * '打开对话窗'事件.
     * @param	session 	对话
     */
    public void sessionOpened(RobotSession session) {
    	System.out.println("EVENT: sessionOpened (" + session.getUser().getClientID() + "," + session.getUser().getStatus() + ")");
        try {
            if (RobotSession.OPEN_MODE_CONV_OPEN == session.getOpenMode()) {
                session.send(commandList);		//发送欢迎语.
            } else if(RobotSession.OPEN_MODE_ROBOT == session.getOpenMode()) {
            	try {
					Thread.sleep(3000) ;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	session.send("i'm a bot!") ;
            }
        } catch (RobotException e) {
            e.printStackTrace();
        }
    }

    /**
     * '对话窗关闭'事件.
     * @param	session		对话
     */
    public void sessionClosed(RobotSession session) {
    	System.out.println("EVENT: sessionClosed");

    }

    /**
     * '收到消息'事件
     * @param	session		对话
     * @param	message		消息
     */
    public void messageReceived(RobotSession session, RobotMessage message) {
        
    	System.out.println("EVENT: messageReceived");
        try {
        	//取得命令.
        	String command = message.getString();
        	
        	//生成一个将要发送给用户的消息对象.
        	RobotMessage msg = session.createMessage();
            
        	//如果是帮助信息, 则发送菜单.
            if ("help".equalsIgnoreCase(command) || "?".equalsIgnoreCase(command)) {
            	session.send(commandList);
            }
            else if ("preface".equalsIgnoreCase(command)) {
            	msg.setSignature("preface-" + new Random().nextInt(100));
            	msg.setString("test change preface");
            	session.send(msg);
            }
            else if ("emo".equalsIgnoreCase(command)) {
            	msg.registerEmoticon("(i)","__default.dat");	//注册图示
            	msg.setString("(i)");
            	session.send(msg);	//发送包含图示的消息
            }
            else if ("nudge".equalsIgnoreCase(command)) {	
            	//session.sendNudge();	//发送振动
            	String k = "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" ;
            	session.sendWink("test1", k) ;
            }
            else if ("p4".equalsIgnoreCase(command)) {
            	session.sendActivity("http://www.google.com","INCE SP HOME PAGE");		//发送活动
            }
            else if ("typing".equalsIgnoreCase(command)) {
            	session.sendTyping();	//发送'正在输入'消息.
            }
            else if ("name".equalsIgnoreCase(command)) {
            	server.setDisplayName("displayname-" + new Random().nextInt(100));	//改变msn昵称.
            }
            else if ("pm".equalsIgnoreCase(command)) {
            	server.setPersonalMessage("personalmessage-" + new Random().nextInt(100));	//改变msn个人消息.
            }
            else if ("dp".equalsIgnoreCase(command)) {
            	server.setDisplayPicture("__default.dat");	//设置msn头像
            }
            else if ("e".equalsIgnoreCase(command)) {
            	//注册图示
            	msg.registerEmoticon("(1)","bear.png");	
            	msg.registerEmoticon("(2)","beaver.png");
            	msg.registerEmoticon("(3)","balloon.png");
            	//写入图示
            	msg.setString("a(1)b(2)c(3)d");
            	
            	//发送图示消息
            	session.send(msg);
            } else if(command.startsWith("invite")) {
            	
             	String[] arr = command.split(" ") ;
            	if(arr.length == 2) {
            		String account = arr[1] ;
            		session.inviteUser(account) ;	
            	}
            
            } else if(command.startsWith("fl")) {
            	server.requestContactList(session.getRobot()) ;
            } else if(command.startsWith("push")) {
            	String[] arr = command.split(" ") ;
            	if(arr.length == 2) {
            		String account = arr[1] ;
            		server.pushMessage(session.getRobot(), account, "hello") ;
            	}
            } else if(command.startsWith("call")) {
               	String[] arr = command.split(" ") ;
            	if(arr.length == 2) {
            		String account = arr[1] ;
//            		for(int i=0; i<100; i++)
//            			server.createSession(session.getRobot(), i + account) ;
            		
            		server.createSession(session.getRobot(), account) ;
            	}
            } else {
            	String ret = "font name: " + message.getFontName() + "\r";
                ret = ret + "font style: " + message.getFontStyle() + "\r";
                ret = ret + "font color: ";
                Color fc = message.getFontColor();
                if (fc != null) {
                    String hexC = Integer.toHexString((0xFFFFFF & fc.getRGB()));
                    int length = 6-hexC.length();
                    for (int k=0;k<length;k++)hexC = "0" + hexC;
                    ret = ret + "0x" + hexC;
                }
                ret = ret + "\r";
                ret = ret + "message content: " + "\r";
                ret = ret + message.getString();
                
                session.send(ret);
            }
        } catch (RobotException e) {
            e.printStackTrace();
        }
    }

    /**
     * '闪屏振动'事件.
     * @param	session		对话
     */
    public void nudgeReceived(RobotSession session) {
    	System.out.println("EVENT: nudgeReceived");
    	try {
			//session.send("nudge received!");
			session.sendActivity("http://www.baidu.com","Baidu Page");
		} catch (RobotException e) {
			e.printStackTrace();
		}	
    }

    /**
     * '活动邀请被接受'事件.
     * @param	session		对话
     */
    public void activityAccepted(RobotSession session) {
    	System.out.println("EVENT: activityAccepted");
    }

    /**
     * '活动邀请被拒绝'事件.
     * @param	session		对话
     */
    public void activityRejected(RobotSession session) {
    	System.out.println("EVENT: activityRejected");
    }
    
    /**
     * '异常'事件.
     * @param	session		对话
     * @param	cause		异常
     */
	public void exceptionCaught(RobotSession session, Throwable cause) {
		System.out.println("SERVER ERROR: " + cause.getMessage());
	}
	
	/**
	 * '用户添加机器人'事件.
	 * @param robot 机器人帐号
	 * @param user  用户帐号
	 * @deprecated Only available in enterprise version 
	 */
	public void userAdd(String robot,String user) {
		System.out.println("EVENT: userAdd (" + user + ")");
	}

	/**
	 * '用户删除机器人'事件.
	 * @param robot 机器人帐号
	 * @param user	用户帐号
	 */
	public void userRemove(String robot, String user) {
		System.out.println("EVENT: userRemove (" + user + ")");
	}
	
	/**
	 * '活动关闭'事件.
	 * @param	session		对话
	 */
	public void activityClosed(RobotSession session) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * '活动被加载'事件
	 * @param	session		对话
	 */
	public void activityLoaded(RobotSession session) {
		// TODO Auto-generated method stub
	}

	/**
	 * '活动发送消息到机器人'事件
	 * @param	session		对话
	 * @param	data		数据
	 * @deprecated Only available in enterprise version 
	 */
	public void activityReceived(RobotSession session, String data) {
	}

	/** 
	 * '背景共享被接受'事件.
	 * @param	session		对话
	 * @deprecated Only available in enterprise version 
	 */
	public void backgroundAccepted(RobotSession session) {
	}

	/**
	 * '背景共享被拒绝'事件.
	 * @param	session		对话
	 * @deprecated Only available in enterprise version 
	 */
	public void backgroundRejected(RobotSession session) {
	}

	/**
	 * '背景传送完毕'事件.
	 * @param	session		对话
	 * @deprecated Only available in enterprise version 
	 */
	public void backgroundTransferEnded(RobotSession session) {
	}

	/**
	 * '文件被接受'事件.
	 * @param	session		对话
	 * @deprecated Only available in enterprise version 
	 */
	public void fileAccepted(RobotSession session) {
	}
	
	/**
	 * '传送文件被拒绝'事件.
	 * @param	session		对话
	 * @deprecated Only available in enterprise version 
	 */
	public void fileRejected(RobotSession session) {
	}

	/**
	 * '传送文件结束'事件.
	 * @param	session		对话
	 * @deprecated Only available in enterprise version 
	 */
	public void fileTransferEnded(RobotSession session) {
	}

	/**
	 * '用户加入对话'事件.
	 * @param	session		对话
	 * @param	user		用户
	 * @deprecated Only available in enterprise version 
	 */
	public void userJoined(RobotSession session, RobotUser user) {	
	}

	/**
	 * '用户离开'事件.
	 * @param	session		对话
	 * @param	user		用户
	 * @deprecated Only available in enterprise version 
	 */
	public void userLeft(RobotSession session, RobotUser user) {
	}

	
	/**
	 * '用户接受视频'事件
	 * @param	session		对话
	 * @deprecated Only available in enterprise version 
	 */
	public void webcamAccepted(RobotSession session) {
	}
	
	/**
	 * '用户拒绝视频'事件
	 * @param	session		对话
	 * @deprecated Only available in enterprise version 
	 */
	public void webcamRejected(RobotSession session) {
	}

	public void userUpdated(RobotUser user) {
		System.out.println("EVENT: userUpdated (" + user + ")");
	}

	public void personalMessageUpdated(String robot, String user,
			String personalMessage) {
		System.out.println("EVENT: personalMessageUpdated (" + robot + ", " + user + ", " + personalMessage + ")");
		
	}

	public void contactListReceived(String robot, List<RobotUser> friendList) {
		
		System.out.println("EVENT: friendListReceived(" + robot + ", list.size()=" + friendList.size() + ")") ;
		
		for(RobotUser u : friendList) {
			System.out.println(u) ;
		}
		
	}


}
